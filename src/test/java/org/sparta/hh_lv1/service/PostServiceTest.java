package org.sparta.hh_lv1.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sparta.hh_lv1.dto.PostRequestDto;
import org.sparta.hh_lv1.dto.PostResponseDto;
import org.sparta.hh_lv1.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class PostServiceTest {
    @Autowired
    PostService postService;

    @Test
    @DisplayName("1-1 게시글 쓰기 정상 테스트")
    void createPostSuccess() {
        PostRequestDto post = new PostRequestDto();

        post.setTitle("제목");
        post.setAuthor("작성자");
        post.setPassword("비밀번호");
        post.setContents("글 내용");

        postService.createPost(post);
    }

    @Test
    @DisplayName("2-1 게시글 전체 조회 테스트")
    void getAllPostSuccess() {
        List<PostResponseDto> post = postService.getAllPost();

        assertThat(post).isNotNull().hasSize(2);
    }

    @Test
    @DisplayName("3-1 게시글 상세 조회 테스트")
    void getDetailPostSuccess() {
        PostResponseDto post = postService.getPost(13L);

        assertThat(post).isNotNull();
        assertThat(post.getTitle()).isNotNull().isNotEmpty();
        assertThat(post.getAuthor()).isNotNull().isNotEmpty();
        assertThat(post.getContents()).isNotNull().isNotEmpty();
    }

    @Test
    @DisplayName("3-2 게시글 상세 조회 실패 테스트")
    void getDetailPostFailed() {
        assertThrows(IllegalArgumentException.class, () -> {
            postService.getPost(999L);
        });
    }

    @Test
    @DisplayName("4-1 게시글 업데이트 테스트")
    void updatePostSuccess() {
        PostRequestDto post = new PostRequestDto();
        post.setTitle("초기 제목");
        post.setAuthor("초기 이름");
        post.setPassword("초기 비밀번호");
        post.setContents("초기 글");
        PostResponseDto createdPost = postService.createPost(post);


        PostRequestDto updateTarget = new PostRequestDto();
        updateTarget.setTitle("변경된 제목");
        updateTarget.setAuthor("변경된 이름");
        updateTarget.setPassword("초기 비밀번호");
        updateTarget.setContents("변경된 글");

        PostResponseDto updatedPost = postService.updatePost(createdPost.getPostId(), updateTarget);

        assertThat(updatedPost).isNotNull();
        assertThat(updatedPost.getTitle()).isEqualTo(updateTarget.getTitle());
        assertThat(updatedPost.getAuthor()).isEqualTo(updateTarget.getAuthor());
        assertThat(updatedPost.getContents()).isEqualTo(updateTarget.getContents());

    }

}