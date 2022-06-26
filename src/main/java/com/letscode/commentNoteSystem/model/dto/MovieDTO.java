package com.letscode.commentNoteSystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class MovieDTO {
    private Long id;
    private String title;
    private String year;
    private Double rate;
    private Integer totalReviews;
}
