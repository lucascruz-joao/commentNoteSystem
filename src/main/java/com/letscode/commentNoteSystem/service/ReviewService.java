package com.letscode.commentNoteSystem.service;


import com.letscode.commentNoteSystem.model.Client;
import com.letscode.commentNoteSystem.model.Review;
import com.letscode.commentNoteSystem.model.dto.ReviewDTO;
import com.letscode.commentNoteSystem.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ClientService clientService;

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
                .build();
        review.getReviews().add(reply);
        reviewRepository.save(review);
    }
}
