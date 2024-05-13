package com.blogapp12.repository;

import com.blogapp12.entity.Comment;
import com.blogapp12.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long>
{


}
