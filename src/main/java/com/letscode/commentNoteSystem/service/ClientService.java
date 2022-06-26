package com.letscode.commentNoteSystem.service;

import com.letscode.commentNoteSystem.model.Client;
import com.letscode.commentNoteSystem.model.dto.ClientDTO;
import com.letscode.commentNoteSystem.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;

    public void save(Client client){
        this.clientRepository.save(client);
    }

    public void createClientFromDTO(ClientDTO clientDTO){
        Client client = this.modelMapper.map(clientDTO, Client.class);
        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        this.clientRepository.save(client);
    }
}
