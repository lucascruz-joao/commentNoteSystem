package com.letscode.commentNoteSystem.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class ReviewDTO {
    private Long id;
    @NotBlank @NotNull
    private String comment;
    @JsonProperty("name")
    private String clientName;
    private List<ReviewDTO> reviews;
    private Boolean repeated;
    private String citedReviewComment;
}
