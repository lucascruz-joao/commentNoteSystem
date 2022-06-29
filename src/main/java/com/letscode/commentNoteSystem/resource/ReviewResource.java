package com.letscode.commentNoteSystem.resource;

import com.letscode.commentNoteSystem.model.dto.ReviewCitedDTO;
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

    private final ReviewService reviewService;

    @PostMapping("{movieId}/{userId}")
    public ResponseEntity<?> createReviewFromMovie(@PathVariable("movieId") Long movieId, @PathVariable("userId") Long userId,
                                                   @Valid @RequestBody ReviewCitedDTO reviewCitedDTO) {
        reviewService.addReviewByMovieId(movieId, userId, reviewCitedDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("{userId}/{reviewId}/reply")
    public ResponseEntity<?> createReply(@PathVariable("userId") Long userId,
                                         @PathVariable("reviewId") Long reviewId,
                                         @Valid @RequestBody ReviewCitedDTO reviewCitedDTO) {
        reviewService.addReplyOnReview(reviewId, userId, reviewCitedDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{movieId}/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable("movieId") Long movieId,
                                             @PathVariable("reviewId") Long reviewId) {
        reviewService.deleteReviewId(movieId, reviewId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{reviewId}/{replyId}/reply")
    public ResponseEntity<Void> deleteReply(@PathVariable("reviewId") Long reviewId,
                                             @PathVariable("replyId") Long replyId) {
        reviewService.deleteReplyById(reviewId, replyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
