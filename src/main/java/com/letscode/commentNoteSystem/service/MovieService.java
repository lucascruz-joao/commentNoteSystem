package com.letscode.commentNoteSystem.service;

import com.letscode.commentNoteSystem.model.Movie;
import com.letscode.commentNoteSystem.model.dto.MovieDTO;
import com.letscode.commentNoteSystem.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    public List<MovieDTO> getAll() {
        List<Movie> all = this.movieRepository.findAll();
        return Arrays.stream(modelMapper.map(all, MovieDTO[].class)).collect(Collectors.toList());
    }
}
