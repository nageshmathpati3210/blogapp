package com.blogapp12.service.serviceimpl;

import com.blogapp12.entity.Comment;
import com.blogapp12.entity.Post;
import com.blogapp12.payload.CommentDto;
import com.blogapp12.payload.ListCommentPostDto;
import com.blogapp12.payload.PostDto;
import com.blogapp12.repository.CommentRepository;
import com.blogapp12.repository.PostRepository;
import com.blogapp12.service.CommentService;
import com.blogapp12.service.PostService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService
{

    private CommentRepository commentRepository;
    private ModelMapper modelMapper;

    private PostRepository postRepository;








    Comment mapToEntity(CommentDto commentDto)
    {
        return  modelMapper.map(commentDto, Comment.class);
    }


    CommentDto mapToDto(Comment comment)
    {
        return modelMapper.map(comment, CommentDto.class);
    }


    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {

        Post post = postRepository.findById(postId).get();
        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        Comment save = commentRepository.save(comment);
        CommentDto commentDto1 = mapToDto(save);
        return commentDto1;
    }

    @Override
    public ListCommentPostDto getAllcomments(long postId)
    {

        Post post = postRepository.findById(postId).get();
        PostDto p=new PostDto();
        p.setId(post.getId());
        p.setName(post.getName());
        p.setDescription(post.getDescription());
        p.setMessage(post.getMessage());
        List<Comment> byPostId = commentRepository.findByPostId(postId);

        List<CommentDto> collect = byPostId.stream().map(e -> mapToDto(e)).collect(Collectors.toList());

        ListCommentPostDto lp=new ListCommentPostDto();
        lp.setPostDto(p);

        lp.setCommentDtoList(collect);

        return lp;

    }
}
