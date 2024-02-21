package org.sparta.hh_lv1.service;

import org.sparta.hh_lv1.dto.PostRequestDto;
import org.sparta.hh_lv1.dto.PostResponseDto;
import org.sparta.hh_lv1.entity.Post;
import org.sparta.hh_lv1.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public PostResponseDto createPost(PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto);

        Post savePost = postRepository.save(post);

        return new PostResponseDto(post);
    }
}
