package com.letscode.commentNoteSystem.resource;

import com.letscode.commentNoteSystem.model.Client;
import com.letscode.commentNoteSystem.model.Review;
import com.letscode.commentNoteSystem.model.dto.ClientDTO;
import com.letscode.commentNoteSystem.model.enums.ClientTypeEnum;
import com.letscode.commentNoteSystem.model.enums.ReviewTypeEnum;
import com.letscode.commentNoteSystem.service.ClientService;
import com.letscode.commentNoteSystem.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ROLE_AVANCADO') or hasRole('ROLE_MODERADOR')")
    public ResponseEntity<?> addLikeOrDislikeReview(@PathVariable("userId") Long userId, @PathVariable("reviewId") Long reviewId,
                                                    @PathVariable("type") ReviewTypeEnum reviewTypeEnum) {
        Review review = reviewService.getById(reviewId);
        this.clientService.likeOrDislikeReview(userId, review, reviewTypeEnum);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("{userId}/{role}")
    @PreAuthorize("hasRole('ROLE_MODERADOR')")
    public ResponseEntity<?> changeClientRole(@PathVariable("userId") Long userId,
                                                    @PathVariable("role") ClientTypeEnum clientTypeEnum) {
        this.clientService.changeClientRole(userId, clientTypeEnum);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}