package com.letscode.commentNoteSystem.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class ClientDTO {
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
}
