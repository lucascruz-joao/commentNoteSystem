package com.letscode.commentNoteSystem.resource;

import com.letscode.commentNoteSystem.model.Movie;
import com.letscode.commentNoteSystem.model.Review;
import com.letscode.commentNoteSystem.model.dto.ClientDTO;
import com.letscode.commentNoteSystem.model.enums.ReviewTypeEnum;
import com.letscode.commentNoteSystem.service.ClientService;
import com.letscode.commentNoteSystem.service.MovieService;
import com.letscode.commentNoteSystem.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/clients")
@AllArgsConstructor
public class ClientResource {
    private final ClientService clientService;
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> createClient(@Valid @RequestBody ClientDTO client) {
        this.clientService.createClientFromDTO(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("{userId}/{reviewId}/{type}")
    public ResponseEntity<?> addMovie(@PathVariable("userId") Long userId, @PathVariable("reviewId") Long reviewId,
                                      @PathVariable("type") ReviewTypeEnum reviewTypeEnum) {
        Review review = reviewService.getById(reviewId);
        this.clientService.likeOrDislikeReview(userId, review, reviewTypeEnum);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}