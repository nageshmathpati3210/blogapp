package com.blogapp12.controller;


import com.blogapp12.payload.ListPostDto;
import com.blogapp12.payload.PostDto;
import com.blogapp12.service.PostService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController
{

    private PostService postService;


    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST);
        }
        else {
            PostDto newPost = postService.createNewPost(postDto);

            return new ResponseEntity<>(newPost, HttpStatus.CREATED);
        }

    }

    //http://localhost:8080/api/posts?postId=
    @DeleteMapping
    public ResponseEntity<?> deletePost(@RequestParam long postId)
    {
        boolean byId = postService.findById(postId);
        if(byId)
        {
            postService.deleteById(postId);
            return new ResponseEntity<>("Deleted sucessfully",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Invalid Id",HttpStatus.BAD_REQUEST);
        }
    }

    //http://localhost:8080/api/posts?pageNo=0&pageSize=3&sortBy="id"&sortDir="desc"

    @GetMapping
    public ResponseEntity<?> getAllPost(@RequestParam(name="pageNo",defaultValue = "0",required = false)long pageNo,
                                        @RequestParam(name="pageSize",defaultValue = "5" ,required = false)long pageSize,
                                        @RequestParam(name="sortBy",defaultValue = "id",required = false)String sortBy ,
                                        @RequestParam(name="sortDir",defaultValue = "asc",required = false)String sortDir)
    {
        ListPostDto allPosts = postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allPosts,HttpStatus.OK);
    }


}
