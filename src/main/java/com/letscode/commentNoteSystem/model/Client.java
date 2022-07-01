package com.letscode.commentNoteSystem.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @CreationTimestamp
    private Date created_at;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String name;
    @NotNull
    private String password;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Point> points;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Review> likeReview;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Review> dislikeReviews;
}