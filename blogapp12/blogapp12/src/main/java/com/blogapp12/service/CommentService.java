package com.blogapp12.service;

import com.blogapp12.payload.CommentDto;
import com.blogapp12.payload.ListCommentPostDto;

public interface CommentService
{
    public CommentDto createComment(CommentDto commentDto, long postId);


    public ListCommentPostDto getAllcomments(long postId);

}
