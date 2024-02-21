package org.sparta.hh_lv1.dto;

import lombok.Getter;
import org.sparta.hh_lv1.entity.Post;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private String title;
    private String author;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.contents = post.getContents();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
    }
}

