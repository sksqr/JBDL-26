package com.geeksforgeeks.minor.l12_visitor_app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
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

    @Schema(type = "string", example = "2022-02-26 23:45")
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime inTime;

    @Schema(type = "string", example = "2022-02-26 23:45")
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime outTime;

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
