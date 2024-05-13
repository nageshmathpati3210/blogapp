package com.blogapp12.payload;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoginDto
{
    private String username;

    private String password;
}
