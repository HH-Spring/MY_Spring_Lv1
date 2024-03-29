package org.sparta.hh_lv1.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sparta.hh_lv1.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

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

    @Test
    @DisplayName("단일 select 테스트")
    void detailSelectTest() {
        Optional<Post> postOptional = postRepository.findById(4L);

        if(postOptional.isPresent()) {
            // 값이 존재 하는지 확인
            assertThat(postOptional).isPresent();

            // null 이 아닌지 확인
            assertThat(postOptional.get()).isNotNull();
        } else {
            // 값이 존재하지 않음을 확인
            assertThat(postOptional).isEmpty();
        }
    }

    @Test
    @DisplayName("Post 업데이트 테스트")
    void updatePostTest() {
        Post post = new Post();

        String updatedTitle = "바뀐 제목";
        String updatedContents = "바뀐 내용";

        post.setPostId(13L);
        post.setTitle("원본 제목");
        post.setAuthor("원본 이름");
        post.setPassword("원본 비밀번호");
        post.setContents("원본 내용");

        postRepository.save(post);

        Long postId = post.getPostId();

        Post updateTarget = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found"));
        updateTarget.setTitle(updatedTitle);
        updateTarget.setContents(updatedContents);
        postRepository.save(updateTarget);

        Post updatedPost = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found"));
        assertThat(updatedPost.getTitle()).isEqualTo(updatedTitle);
        assertThat(updatedPost.getContents()).isEqualTo(updatedContents);
    }

    @Test
    @DisplayName("Post 삭제 테스트")
    void deletePostTest() {
        Post post = new Post();
        post.setPostId(15L);
        post.setTitle("제목");
        post.setAuthor("이름");
        post.setPassword("비밀번호");
        post.setContents("내용");

        Post savedPost = postRepository.save(post);

        postRepository.delete(savedPost);

        Optional<Post> deletedPost = postRepository.findById(savedPost.getPostId());
        assertThat(deletedPost).isEmpty();
    }
}