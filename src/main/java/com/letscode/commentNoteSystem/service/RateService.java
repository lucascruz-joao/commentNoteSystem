package com.letscode.commentNoteSystem.service;

import com.letscode.commentNoteSystem.model.Rate;
import com.letscode.commentNoteSystem.model.dto.RateDTO;
import com.letscode.commentNoteSystem.repository.RateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RateService {
    private final RateRepository rateRepository;
    private final MovieService movieService;

    private final ClientService clientService;

    public void saveRate(Long movieId, RateDTO rateDTO) {
        Rate rate = null;
        rate = this.movieService.checkRateOnMovieByClient(movieId, rateDTO.getUserId());
        if (rate != null) {
            rate.setValue(rateDTO.getValue());
            rateRepository.save(rate);
        } else {
            rate = Rate.builder()
                    .value(rateDTO.getValue())
                    .client(clientService.getById(rateDTO.getUserId()))
                    .build();
            this.movieService.addMovieRate(movieId, rate);
        }
    }
}
