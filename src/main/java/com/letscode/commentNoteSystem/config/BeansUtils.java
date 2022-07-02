package com.letscode.commentNoteSystem.config;

import com.letscode.commentNoteSystem.model.Movie;
import com.letscode.commentNoteSystem.model.dto.OmdbDTO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeansUtils {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(OmdbDTO.class, Movie.class).addMappings(
                mapper -> {
                    mapper.map(OmdbDTO::getYear, Movie::setYear);
                    mapper.map(OmdbDTO::getTitle, Movie::setTitle);
                    mapper.map(OmdbDTO::getId, Movie::setId);
                }
        );
        return modelMapper;
    }
}