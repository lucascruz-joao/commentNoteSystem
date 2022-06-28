package com.letscode.commentNoteSystem.service;

import com.letscode.commentNoteSystem.model.Client;
import com.letscode.commentNoteSystem.model.Movie;
import com.letscode.commentNoteSystem.model.Rate;
import com.letscode.commentNoteSystem.model.Review;
import com.letscode.commentNoteSystem.model.dto.MovieDTO;
import com.letscode.commentNoteSystem.model.dto.ReviewDTO;
import com.letscode.commentNoteSystem.model.enums.OriginPointEnum;
import com.letscode.commentNoteSystem.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;
    private final PointService pointService;
    private final ClientService clientService;

    public List<MovieDTO> getAll() {
        List<Movie> all = this.movieRepository.findAll();
        return Arrays.stream(modelMapper.map(all, MovieDTO[].class)).collect(Collectors.toList());
    }

    public Movie getMovieById(Long movieId) {
        Optional<Movie> movieDb = this.movieRepository.findById(movieId);
        if(movieDb.isPresent())
            return movieDb.get();
        throw new RuntimeException("Filme não encontrado");
    }

    public void addMovieRate(Long movieId, Rate rate) {
        Movie movie = this.getMovieById(movieId);
        movie.getRates().add(rate);
        this.movieRepository.save(movie);
        pointService.addPointToUser(rate.getClient(), 1L, OriginPointEnum.RATE);
    }

    public Rate checkRateOnMovieByClient(Long movieId, Long clientId) {
        Movie movie = this.getMovieById(movieId);
        //TODO: Melhorar essa busca usando o repository
        Optional<Rate> rate = movie.getRates().stream().filter(rateItem -> rateItem.getClient().getId().equals(clientId)).findFirst();
        return rate.orElse(null);
    }

    public List<ReviewDTO> getAllReviewsByMovieId(Long movieId) {
        List<Review> reviews = this.getMovieById(movieId).getReviews();
        return Arrays.stream(modelMapper.map(reviews, ReviewDTO[].class)).collect(Collectors.toList());
    }

    public void addReviewByMovieId(Long movieId, Long userId, ReviewDTO reviewDTO) {
        Movie movie = this.getMovieById(movieId);
        Client client = clientService.getById(userId);
        Review review = Review.builder()
                .client(client)
                .comment(reviewDTO.getComment())
                .repeated(false)
                .delete(false)
                .build();

        movie.getReviews().add(review);
        this.movieRepository.save(movie);

    }
}
