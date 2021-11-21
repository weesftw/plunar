package com.weesftw.scaffold.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionDTO
{
    private Integer errorCode;
    private String description;
    private OffsetDateTime time;
    private List<String> errors;
}
