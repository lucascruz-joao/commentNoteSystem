package com.letscode.commentNoteSystem.resource;

import com.letscode.commentNoteSystem.model.dto.ClientDTO;
import com.letscode.commentNoteSystem.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/clients")
@AllArgsConstructor
public class ClientResource {
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<?> createClient(@Valid @RequestBody ClientDTO client) {
        this.clientService.createClientFromDTO(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}