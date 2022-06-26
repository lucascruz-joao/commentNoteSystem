package com.letscode.commentNoteSystem.resource;

import com.letscode.commentNoteSystem.model.Movie;
import com.letscode.commentNoteSystem.model.dto.ClientDTO;
import com.letscode.commentNoteSystem.model.enums.MovieTypeEnum;
import com.letscode.commentNoteSystem.service.ClientService;
import com.letscode.commentNoteSystem.service.MovieService;
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
    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<?> createClient(@Valid @RequestBody ClientDTO client) {
        this.clientService.createClientFromDTO(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("{userId}/{movieId}/{type}")
    public ResponseEntity<?> addMovie(@PathVariable("userId") Long userId, @PathVariable("movieId") Long movieId,
                                      @PathVariable("type")MovieTypeEnum movieTypeEnum) {
        Movie movie = movieService.getMovieById(movieId);
        this.clientService.addMovie(userId, movie, movieTypeEnum);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}