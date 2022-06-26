package com.letscode.commentNoteSystem.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String comment;

    @ManyToOne
    private Client client;

    @ManyToMany
    private List<Review> reviews;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean repeated;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean delete;
}
