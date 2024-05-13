package com.blogapp12.payload;

import com.blogapp12.entity.Post;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCommentPostDto
{


    @JsonProperty("post")
    private PostDto postDto;


    private List<CommentDto> commentDtoList;

}
