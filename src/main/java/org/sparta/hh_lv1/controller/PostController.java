package org.sparta.hh_lv1.controller;

import org.sparta.hh_lv1.dto.PostRequestDto;
import org.sparta.hh_lv1.dto.PostResponseDto;
import org.sparta.hh_lv1.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto) {
        return this.postService.createPost(postRequestDto);
    }

    @GetMapping("/post")
    public List<PostResponseDto> getAllPost() {
        System.out.println("test");
        return this.postService.getAllPost();
    }

    @GetMapping("/post/{postId}")
    public PostResponseDto getPost(@PathVariable Long postId) {
        return this.postService.getPost(postId);
    }

}
