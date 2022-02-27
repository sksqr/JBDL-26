package com.geeksforgeeks.minor.l12_visitor_app.model;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FlatsDTO {

    private Long id;

    @Size(max = 255)
    private String number;

}
