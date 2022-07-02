package com.letscode.commentNoteSystem.model;

import javax.validation.constraints.NotNull;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @NotBlank
    @Column(columnDefinition = "TEXT")
    private String comment;

    @ManyToOne
    private Client client;

    @ManyToMany(cascade = {CascadeType.ALL, CascadeType.REMOVE})
    private List<Review> reviews;

    private Boolean repeated = false;

    private Boolean delete = false;

    @ManyToOne
    private Review citedReview;
}
