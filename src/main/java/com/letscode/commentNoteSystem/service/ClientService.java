package com.letscode.commentNoteSystem.service;

import com.letscode.commentNoteSystem.model.Client;
import com.letscode.commentNoteSystem.model.Point;
import com.letscode.commentNoteSystem.model.Review;
import com.letscode.commentNoteSystem.model.dto.ClientDTO;
import com.letscode.commentNoteSystem.model.enums.ReviewTypeEnum;
import com.letscode.commentNoteSystem.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;

    public void save(Client client) {
        this.clientRepository.save(client);
    }

    public void createClientFromDTO(ClientDTO clientDTO) {
        Client client = this.modelMapper.map(clientDTO, Client.class);
        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        this.clientRepository.save(client);
    }

    public Client getById(Long id) {
        Optional<Client> clientDb = clientRepository.findById(id);
        if (clientDb.isPresent())
            return clientDb.get();
        throw new RuntimeException("Cliente não existe");
    }

    public void addPointClient(Client client, Point point) {
        client.getPoints().add(point);
        clientRepository.save(client);
    }

    public void likeOrDislikeReview(Long userId, Review review, ReviewTypeEnum reviewTypeEnum) {
        Client client = this.getById(userId);

        client.getDislikeReviews().remove(review);
        client.getLikeReview().remove(review);

        if (reviewTypeEnum.equals(ReviewTypeEnum.LIKE))
            client.getLikeReview().add(review);
        else
            client.getDislikeReviews().add(review);

        this.clientRepository.save(client);
    }
}