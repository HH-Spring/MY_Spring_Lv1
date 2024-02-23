package org.sparta.hh_lv1.service;

import org.sparta.hh_lv1.dto.PostRequestDto;
import org.sparta.hh_lv1.dto.PostResponseDto;
import org.sparta.hh_lv1.entity.Post;
import org.sparta.hh_lv1.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

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

    public List<PostResponseDto> getAllPost() {
        return this.postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    public PostResponseDto getPost(Long postId) {
//        Optional<Post> postOptional = this.postRepository.findById(postId);
//
//        if(postOptional.isPresent()) {
//            Post post = postOptional.get();
//
//            return new PostResponseDto(post);
//        }
//        return null;
        return findPost(postId);
    }

    public PostResponseDto updatePost(Long postId, PostRequestDto postRequestDto) {
        Post post = this.findNormalPost(postId);

        if(!Objects.equals(post.getPassword(), postRequestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }

        post.update(postRequestDto);

        return new PostResponseDto(post);
    }


    public PostResponseDto findPost(Long postId) {
        return this.postRepository.findById(postId)
                .map(PostResponseDto::new)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
    }

    public Post findNormalPost(Long postId) {
        return this.postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
    }
}
