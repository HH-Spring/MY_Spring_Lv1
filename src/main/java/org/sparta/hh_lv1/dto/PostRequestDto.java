package org.sparta.hh_lv1.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String title;
    private String author;
    private String password;
    private String contents;
}
