package com.blogapp12.payload;


import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class PostDto
{
    private long id;

    @Size(min=3,message = "Minimum size of name is 5")
    private String name;

    @Size(min=3,message = "Minimum size of message is 5")
    private String message;

    private String description;
}
