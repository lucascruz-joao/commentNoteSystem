package com.letscode.commentNoteSystem.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
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
