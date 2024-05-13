package com.blogapp12.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class ErrorFlow
{

    private LocalDateTime localDateTime;

    private String message;

    private String description;
}
