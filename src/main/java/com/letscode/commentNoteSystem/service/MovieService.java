package com.letscode.commentNoteSystem.service;

import com.letscode.commentNoteSystem.model.Movie;
import com.letscode.commentNoteSystem.model.Rate;
import com.letscode.commentNoteSystem.model.Review;
import com.letscode.commentNoteSystem.model.dto.MovieDTO;
import com.letscode.commentNoteSystem.model.dto.OmdbDTO;
import com.letscode.commentNoteSystem.model.dto.ReviewDTO;
import com.letscode.commentNoteSystem.model.enums.OriginPointEnum;
import com.letscode.commentNoteSystem.repository.MovieRepository;
import com.letscode.commentNoteSystem.repository.OmdbRepository;
import org.jetbrains.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final OmdbRepository omdbRepository;
    private final ModelMapper modelMapper;
    private final PointService pointService;

    private final MovieRepository movieRepository;

    public MovieService(ModelMapper modelMapper, PointService pointService, MovieRepository movieRepository) {
        this.modelMapper = modelMapper;
        this.pointService = pointService;
        this.movieRepository = movieRepository;
        String baseUrl = "https://www.omdbapi.com";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                //.addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        omdbRepository = retrofit.create(OmdbRepository.class);
    }

    public List<MovieDTO> getAll() {
        List<Movie> all = this.movieRepository.findAll();
        return Arrays.stream(modelMapper.map(all, MovieDTO[].class)).collect(Collectors.toList());
    }

    public Movie getMovieById(String movieId){
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if (movieOptional.isPresent())
            return movieOptional.get();

        try {
            Movie movieOnOmdb = getMovieOnOmdb(movieId);
            if (movieOnOmdb != null)
                return movieOnOmdb;
            throw new RuntimeException("Filme não encontrado");
        } catch (Exception exception){
            return null;
        }
    }

    @Nullable
    private Movie getMovieOnOmdb(String movieId) throws IOException {
        String apikey = "2b28c78a";
        Call<OmdbDTO> movieDb = this.omdbRepository.getMovieById(apikey, movieId);
        Response<OmdbDTO> execute = movieDb.execute();
        if (execute.isSuccessful()){
            return saveFromOmdbDto(execute.body());
        }
        return null;
    }

    private Movie saveFromOmdbDto(OmdbDTO omdbDTO){
        Movie movie = this.modelMapper.map(omdbDTO, Movie.class);
        return this.movieRepository.save(movie);
    }

    public void addMovieRate(String movieId, Rate rate) {
        Movie movie = this.getMovieById(movieId);
        movie.getRates().add(rate);
        this.movieRepository.save(movie);
        pointService.addPointToUser(rate.getClient(), 1L, OriginPointEnum.RATE);
    }

    public void addReview(String movieId, Review review) {
        Movie movie = this.getMovieById(movieId);
        movie.getReviews().add(review);
        this.movieRepository.save(movie);
    }

    public Rate checkRateOnMovieByClient(String movieId, Long clientId) {
        Movie movie = this.getMovieById(movieId);
        //TODO: Melhorar essa busca usando o repository
        Optional<Rate> rate = movie.getRates().stream().filter(rateItem -> rateItem.getClient().getId().equals(clientId)).findFirst();
        return rate.orElse(null);
    }

    public List<ReviewDTO> getAllReviewsByMovieId(String movieId) {
        List<Review> reviews = this.getMovieById(movieId).getReviews();
        return Arrays.stream(modelMapper.map(reviews, ReviewDTO[].class)).collect(Collectors.toList());
    }
}
