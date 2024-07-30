package com.admin_panel.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String name;
    private String login;
    private Set<String> permissions;
}