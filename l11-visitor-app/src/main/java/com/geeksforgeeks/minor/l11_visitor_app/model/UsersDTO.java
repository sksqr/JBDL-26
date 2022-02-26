package com.geeksforgeeks.minor.l11_visitor_app.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UsersDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Size(max = 255)
    @Email
    private String email;

    @NotNull
    @Size(max = 255)
    private String phone;

    private Long flat;

    private Long address;

    private Long userRole;

}
