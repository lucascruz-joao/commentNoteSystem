package com.letscode.commentNoteSystem.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ReviewDTO {
    private Long id;
    private String comment;
    @JsonProperty("name")
    private String clientName;
    private List<ReviewDTO> reviews;
    private Boolean repeated;
}
