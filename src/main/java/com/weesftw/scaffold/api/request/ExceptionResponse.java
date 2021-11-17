package com.weesftw.scaffold.api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse
{
    private Integer errorCode;
    private String description;
    private OffsetDateTime time;
    private List<String> errors;
}
