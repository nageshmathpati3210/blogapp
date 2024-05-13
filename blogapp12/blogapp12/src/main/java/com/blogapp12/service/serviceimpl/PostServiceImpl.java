package com.blogapp12.service.serviceimpl;

import com.blogapp12.entity.Post;
import com.blogapp12.exception.UserNotPresentException;
import com.blogapp12.payload.ListPostDto;
import com.blogapp12.payload.PostDto;
import com.blogapp12.repository.PostRepository;
import com.blogapp12.service.PostService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService
{


    private PostRepository postRepository;
    private ModelMapper modelMapper;

    @Override
    public PostDto createNewPost(PostDto postDto)
    {
        Post save = postRepository.save(mapToEntity(postDto));
        return  mapToDto(save);

    }

    @Override
    public boolean findById(long postId)
    {

        Post p = postRepository.findById(postId).orElseThrow(() -> new UserNotPresentException("This Id is Not Present"));
        if(p!=null)
        {
            return true;
        }
        return false;

    }

    @Override
    public void deleteById(long postId)
    {
        Post p = postRepository.findById(postId).orElseThrow(() -> new UserNotPresentException("This Id is Not Present"));
        postRepository.deleteById(postId);
    }

    @Override
    public ListPostDto getAllPosts(long pageNo, long pageSize, String sortBy, String sortDir)
    {

       Sort sort= sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        PageRequest pg= PageRequest.of((int) pageNo, (int) pageSize,sort);

        Page<Post> all = postRepository.findAll(pg);

        List<Post> content = all.getContent();

        List<PostDto> collect = content.stream().map(e -> mapToDto(e)).collect(Collectors.toList());

        ListPostDto l=new ListPostDto();
        l.setTotalPages(all.getTotalPages());
        l.setPostDtoList(collect);
        l.setFirstPage(all.isFirst());
        l.setPageNo(all.getNumber());
        l.setLastPage(all.isLast());
        l.setTotalPages(all.getTotalPages());
        l.setTotalElements((int)all.getTotalElements());

        return l;


    }


    Post mapToEntity(PostDto postDto)
    {
       return  modelMapper.map(postDto,Post.class);
    }


    PostDto mapToDto(Post post)
    {
        return modelMapper.map(post, PostDto.class);
    }



}
