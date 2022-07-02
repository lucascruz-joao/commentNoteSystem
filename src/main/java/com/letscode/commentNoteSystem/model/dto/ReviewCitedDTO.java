package com.letscode.commentNoteSystem.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor @NoArgsConstructor
public class ReviewCitedDTO {
    private Long citedReviewId;
    @NotBlank(message = "informe um comentário")
    @NotNull(message = "informe um comentário")
    private String comment;
}
