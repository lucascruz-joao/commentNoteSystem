package com.letscode.commentNoteSystem.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class MovieDTO {
    private String id;
    private String title;
    private String year;
    private Double rate;
    private Integer totalReviews;
}
