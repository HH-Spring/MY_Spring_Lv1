package org.sparta.hh_lv1.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sparta.hh_lv1.dto.PostRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}