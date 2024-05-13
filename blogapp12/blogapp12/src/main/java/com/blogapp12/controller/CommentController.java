package com.blogapp12.controller;


import com.blogapp12.payload.CommentDto;
import com.blogapp12.payload.ListCommentPostDto;
import com.blogapp12.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comment")
@AllArgsConstructor
public class CommentController
{

    private CommentService commentService;
    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable long postId)
    {

        CommentDto comment = commentService.createComment(commentDto,postId);

        return new ResponseEntity<>(comment, HttpStatus.CREATED);

    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getCommentByPostID(@PathVariable long postId)
    {
        ListCommentPostDto allcomments = commentService.getAllcomments(postId);
        return new ResponseEntity<>(allcomments,HttpStatus.OK);
    }


}
