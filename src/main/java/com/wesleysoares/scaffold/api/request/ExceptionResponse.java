package com.wesleysoares.scaffold.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class ExceptionResponse
{
    private Integer errorCode;
    private String description;
    private OffsetDateTime time;
    private List<String> errors;

    public ExceptionResponse()
    {
        this.time = OffsetDateTime.now();
    }
}
