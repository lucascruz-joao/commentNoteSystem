package com.letscode.commentNoteSystem.model;

import com.letscode.commentNoteSystem.model.enums.ClientTypeEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date created_at;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String name;
    @NotNull
    private String password;
    @Enumerated(EnumType.STRING)
    private ClientTypeEnum type;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Point> points;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Review> likeReview;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Review> dislikeReviews;
}