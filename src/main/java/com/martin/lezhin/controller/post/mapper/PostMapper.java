package com.martin.lezhin.controller.post.mapper;

import com.martin.lezhin.controller.post.dto.PostDto;
import com.martin.lezhin.entity.post.Post;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostMapper {
    public Post dtoToEntity(PostDto postDto) {
        return Post.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .build();
    }

    public PostDto entityToDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .userId(post.getUser().getId())
                .build();
    }

    public List<PostDto> entityListToDtoList(List<Post> followingsPost) {
        List<PostDto> postDtoList = new ArrayList<>();
        followingsPost.forEach(post -> postDtoList.add(entityToDto(post)));

        return postDtoList;
    }
}
