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
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    private String year;

    @ManyToMany
    private List<Rate> rates;

    @ManyToMany
    private List<Review> reviews;

    @Transient
    private Double rate;
    @Transient
    private Integer totalReviews;

    public Double getRate() {
        if(this.rates.size() > 0) {
            double total = this.rates.stream().mapToDouble(Rate::getValue).sum();
            this.rate = total/this.rates.size();
        } else
            this.rate = 0.0;
        return this.rate;
    }

    public Integer getTotalReviews() {
        this.totalReviews = this.reviews.size();
        return this.totalReviews;
    }
}
