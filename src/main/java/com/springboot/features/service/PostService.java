package com.springboot.features.service;

import com.springboot.features.dto.PostDto;
import com.springboot.features.entity.PostEntity;
import com.springboot.features.entity.User;
import com.springboot.features.exception.ResourceNotFound;
import com.springboot.features.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    public List<PostDto> getAllPost(){
        return postRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, PostDto.class))
                .collect(Collectors.toList());
    }

    public PostDto createPost(PostDto postDto) {
        return modelMapper.map(postRepository.save(modelMapper.map(postDto, PostEntity.class)), PostDto.class);
    }

    public PostDto getPost(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info("logged in user from security context : {}", user);
        PostEntity postEntity = postRepository.findById(id).orElseThrow(() -> new ResourceNotFound("No Post Found for id "+ id));
        return modelMapper.map(postEntity, PostDto.class);
    }

    public PostDto updatePost(Long id, PostDto newPost) {
        PostEntity olderPost = postRepository.findById(id).orElseThrow(() ->  new ResourceNotFound("No Post Found for id "+ id));
        newPost.setId(id);
        modelMapper.map(newPost, olderPost);
        return modelMapper.map(postRepository.save(olderPost), PostDto.class);
    }
}
