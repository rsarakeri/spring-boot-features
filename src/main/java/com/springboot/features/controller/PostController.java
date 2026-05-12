package com.springboot.features.controller;

import com.springboot.features.advice.ApiError;
import com.springboot.features.advice.ApiResponse;
import com.springboot.features.dto.PostDto;
import com.springboot.features.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getPost() {
        return ResponseEntity.ok(postService.getAllPost());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostDto>> getPost(@PathVariable Long id) {
        ApiResponse<PostDto> response = ApiResponse.<PostDto>builder()
                .data(postService.getPost(id))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PostDto>> createPost(@RequestBody PostDto postDto) {
        ApiResponse<PostDto> response = ApiResponse.<PostDto>builder()
                .data(postService.createPost(postDto))
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PostDto>> updatePost(@RequestBody PostDto postDto, @PathVariable Long id) {
        ApiResponse<PostDto> response = ApiResponse.<PostDto>builder()
                .data(postService.updatePost(id, postDto))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
