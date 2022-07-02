package com.letscode.commentNoteSystem.resource;

import com.letscode.commentNoteSystem.model.dto.RateDTO;
import com.letscode.commentNoteSystem.service.RateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("rates")
@AllArgsConstructor
public class RateResource {

    private final RateService rateService;

    //TODO: Será utilizado identify do Spring para captura automática do userId
    @PostMapping("{movieId}")
    @PreAuthorize("hasRole('ROLE_LEITOR') or hasRole('ROLE_BASICO') or hasRole('ROLE_AVANCADO') or hasRole('ROLE_MODERADOR')")
    public ResponseEntity<Void> postRate(@PathVariable("movieId") String movieId, @Valid @RequestBody RateDTO rateDTO) {
        this.rateService.saveRate(movieId, rateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
