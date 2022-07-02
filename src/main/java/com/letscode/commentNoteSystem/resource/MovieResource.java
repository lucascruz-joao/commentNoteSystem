package com.letscode.commentNoteSystem.resource;

import com.letscode.commentNoteSystem.model.Movie;
import com.letscode.commentNoteSystem.model.dto.MovieDTO;
import com.letscode.commentNoteSystem.model.dto.ReviewDTO;
import com.letscode.commentNoteSystem.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("movies")
@AllArgsConstructor
public class MovieResource {

    private final MovieService movieService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_LEITOR') or hasRole('ROLE_BASICO') or hasRole('ROLE_AVANCADO') or hasRole('ROLE_MODERADOR')")
    public ResponseEntity<List<MovieDTO>> getAll() {
        List<MovieDTO> all = movieService.getAll();
        if(all.size() != 0)
            return new ResponseEntity<>(all, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{movieId}")
    @PreAuthorize("hasRole('ROLE_LEITOR') or hasRole('ROLE_BASICO') or hasRole('ROLE_AVANCADO') or hasRole('ROLE_MODERADOR')")
    public ResponseEntity<?> getMovieById(@PathVariable("movieId") String movieId) {
        Movie allMovies = movieService.getMovieById(movieId);
        return new ResponseEntity<>(allMovies, HttpStatus.OK);
    }

    @GetMapping("reviews/{movieId}")
    @PreAuthorize("hasRole('ROLE_LEITOR') or hasRole('ROLE_BASICO') or hasRole('ROLE_AVANCADO') or hasRole('ROLE_MODERADOR')")
    public ResponseEntity<?> getAllReviewsByMovieId(@PathVariable("movieId") String movieId) {
        List<ReviewDTO> allReviews = movieService.getAllReviewsByMovieId(movieId);
        return new ResponseEntity<>(allReviews, HttpStatus.OK);
    }
}
