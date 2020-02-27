package com.martin.lezhin.controller.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private Long id;

    private String title;

    private String content;

    private Long userId;
}
