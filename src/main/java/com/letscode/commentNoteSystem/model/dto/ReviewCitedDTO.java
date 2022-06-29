package com.letscode.commentNoteSystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor @NoArgsConstructor
public class ReviewCitedDTO extends ReviewDTO{
    private Long citedReviewId;
}
