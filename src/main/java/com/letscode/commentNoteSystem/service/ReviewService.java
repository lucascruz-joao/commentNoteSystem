package com.letscode.commentNoteSystem.service;


import com.letscode.commentNoteSystem.model.Client;
import com.letscode.commentNoteSystem.model.Movie;
import com.letscode.commentNoteSystem.model.Review;
import com.letscode.commentNoteSystem.model.dto.ReviewCitedDTO;
import com.letscode.commentNoteSystem.model.dto.ReviewDTO;
import com.letscode.commentNoteSystem.model.enums.OriginPointEnum;
import com.letscode.commentNoteSystem.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ClientService clientService;
    private final PointService pointService;
    private final MovieService movieService;

    public Review getById(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isPresent())
            return review.get();
        throw new RuntimeException("Comentário não encontrado");
    }

    public void addReplyOnReview(Long reviewId, Long userId, ReviewDTO reviewDTO) {
        Review review = this.getById(reviewId);
        Client client = clientService.getById(userId);
        Review reply = Review.builder()
                .comment(reviewDTO.getComment())
                .client(client)
                .delete(false)
                .repeated(false)
                .build();
        review.getReviews().add(reply);
        reviewRepository.save(review);
        pointService.addPointToUser(client, 1L, OriginPointEnum.REPLY_REVIEW);
    }

    public void addReviewByMovieId(Long movieId, Long userId, ReviewCitedDTO reviewCitedDTO) {
        Client client = clientService.getById(userId);
        Review citedReview = null;
        if (reviewCitedDTO.getCitedReviewId() != null)
            citedReview = reviewRepository.findById(reviewCitedDTO.getCitedReviewId()).orElse(null);

        Review review = Review.builder()
            .client(client)
            .comment(reviewCitedDTO.getComment())
            .repeated(false)
            .delete(false)
            .citedReview(citedReview)
            .build();

        movieService.addReview(movieId, review);
    }
    public void deleteReviewId(Long movieId, Long reviewId) {
        Movie movie = movieService.getMovieById(movieId);
        Optional<Review> review = movie.getReviews().stream().filter(reviewItem -> reviewItem.getId().equals(reviewId)).findFirst();
        if(review.isPresent()) {
            movie.getReviews().remove(review.get());
            reviewRepository.deleteById(reviewId);
        } else
            throw new RuntimeException("Comentário não encontrado");
    }

    public void deleteReplyById(Long reviewId, Long replyId) {
        Review review = this.getById(reviewId);
        Optional<Review> reply = review.getReviews().stream().filter(reviewItem -> reviewItem.getId().equals(replyId)).findFirst();
        if(reply.isPresent()) {
            review.getReviews().remove(reply.get());
            reviewRepository.save(review);
            reviewRepository.deleteById(reply.get().getId());
        }
        else
            throw new RuntimeException("Resposta não encontrada");
    }
}
