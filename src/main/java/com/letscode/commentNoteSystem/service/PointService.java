package com.letscode.commentNoteSystem.service;

import com.letscode.commentNoteSystem.model.Client;
import com.letscode.commentNoteSystem.model.Point;
import com.letscode.commentNoteSystem.model.enums.OriginPointEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PointService {

    private final ClientService clientService;

    public void addPointToUser(Client client, Long value, OriginPointEnum originPointEnum) {
        Point point = Point.builder()
                .point(value)
                .origin(originPointEnum.name()).build();
        clientService.addPointClient(client, point);
    }
}
