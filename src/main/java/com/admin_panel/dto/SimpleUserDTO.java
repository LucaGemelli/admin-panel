package com.admin_panel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SimpleUserDTO {

    @NotBlank(message = "Name cannot be null or empty")
    private String name;
    @NotBlank(message = "Login cannot be null or empty")
    private String login;
    private Set<String> permissions;
}