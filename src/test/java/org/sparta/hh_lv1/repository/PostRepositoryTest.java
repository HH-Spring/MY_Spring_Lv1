package org.sparta.hh_lv1.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sparta.hh_lv1.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @Test
    @DisplayName("insert 테스트")
    void insertTest() {
        // given
        long preCount = postRepository.count();

        Post post = new Post();

        post.setTitle("제목");
        post.setAuthor("작성자");
        post.setPassword("비밀번호");
        post.setContents("글 내용");

        // when
        Post savePost = postRepository.save(post);

        // then
        assertThat(postRepository.count()).isEqualTo(preCount + 1);

    }

    @Test
    @DisplayName("전체 select 테스트")
    void allSelectTest() {
        List<Post> post = postRepository.findAll();

        assertThat(post).isNotNull().hasSize(2);
    }
}