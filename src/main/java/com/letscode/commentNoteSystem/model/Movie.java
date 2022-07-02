package com.letscode.commentNoteSystem.model;

import javax.validation.constraints.NotNull;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Movie {
    @Id
    private String id;

    @NotNull
    private String title;

    private String year;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Rate> rates;

    @ManyToMany(cascade = CascadeType.ALL)
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
