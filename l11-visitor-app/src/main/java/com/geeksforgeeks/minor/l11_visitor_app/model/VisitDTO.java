package com.geeksforgeeks.minor.l11_visitor_app.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VisitDTO {

    private Long id;

    @NotNull
    private VisitStatus status;

    private LocalDateTime inTime;

    @Schema(type = "string", example = "14:30")
    private LocalTime outTime;

    @NotNull
    @Size(max = 255)
    private String purpose;

    @NotNull
    private Integer noOfPeople;

    @Size(max = 255)
    private String imageUrl;

    private Long flat;

}
