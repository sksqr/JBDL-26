package com.example.L10_jpa_demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonDto {
    private String name;
    private String email;
    private String phone;
}
