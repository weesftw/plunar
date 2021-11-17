package com.weesftw.scaffold.api.request;

import com.weesftw.scaffold.domain.model.User;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
public class UserResponse
{
    private List<User> user;
    private OffsetDateTime dateTime;
}
