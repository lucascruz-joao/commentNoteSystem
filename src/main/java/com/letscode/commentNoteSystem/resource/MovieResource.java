package com.letscode.commentNoteSystem.resource;

import com.letscode.commentNoteSystem.model.dto.MovieDTO;
import com.letscode.commentNoteSystem.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("movies")
@AllArgsConstructor
public class MovieResource {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAll() {
        List<MovieDTO> all = movieService.getAll();
        if(all.size() != 0)
            return new ResponseEntity<>(all, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
