package org.sparta.hh_lv1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String author;
    private String password;
    private String contents;
}
