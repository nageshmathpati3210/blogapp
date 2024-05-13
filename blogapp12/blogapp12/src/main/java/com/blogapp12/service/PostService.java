package com.blogapp12.service;

import com.blogapp12.payload.ListPostDto;
import com.blogapp12.payload.PostDto;

public interface PostService
{

    public PostDto createNewPost(PostDto postDto);


    public boolean findById(long postId);


    public void deleteById(long postId);

    ListPostDto getAllPosts(long pageNo, long pageSize, String sortBy, String sortDir);
}
