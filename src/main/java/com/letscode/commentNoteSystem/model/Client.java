package com.letscode.commentNoteSystem.model;

import com.letscode.commentNoteSystem.model.enums.ClientTypeEnum;
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
    @com.sun.istack.NotNull
    @CreationTimestamp
    private Date created_at;
    @com.sun.istack.NotNull
    @Column(unique = true)
    private String email;
    @com.sun.istack.NotNull
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