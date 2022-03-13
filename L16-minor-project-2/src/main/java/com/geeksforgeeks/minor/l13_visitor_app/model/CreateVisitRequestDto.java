package com.geeksforgeeks.minor.l13_visitor_app.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
public class CreateVisitRequestDto {

    @NotNull
    @Size(max = 255)
    private String purpose;

    @NotNull
    private Integer noOfPeople;

    @Size(max = 255)
    private String imageUrl;

    @NotNull
    private Long flat;

    @NotNull
    private Long visitorId;

}
