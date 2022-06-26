package com.letscode.commentNoteSystem.resource;

import com.letscode.commentNoteSystem.model.dto.ReviewDTO;
import com.letscode.commentNoteSystem.service.MovieService;
import com.letscode.commentNoteSystem.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("reviews")
@AllArgsConstructor
public class ReviewResource {

    private final MovieService movieService;

    private final ReviewService reviewService;

    @PostMapping("{movieId}/{userId}")
    public ResponseEntity<?> createReviewFromMovie(@PathVariable("movieId") Long movieId, @PathVariable("userId") Long userId,
                                                   @Valid @RequestBody ReviewDTO reviewDTO) {
        movieService.addReviewByMovieId(movieId, userId, reviewDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("{userId}/{reviewId}/reply")
    public ResponseEntity<?> createReply(@PathVariable("userId") Long userId,
                                         @PathVariable("reviewId") Long reviewId,
                                         @Valid @RequestBody ReviewDTO reviewDTO) {
        reviewService.addReplyOnReview(reviewId, userId, reviewDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
