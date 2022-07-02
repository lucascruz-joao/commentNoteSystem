package com.letscode.commentNoteSystem.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OmdbDTO {
    @JsonProperty("imdbID")
    private String id;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Year")
    private String year;

}
