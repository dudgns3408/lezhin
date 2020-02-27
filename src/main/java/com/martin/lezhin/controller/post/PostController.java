package com.martin.lezhin.controller.post;

import com.martin.lezhin.controller.post.dto.PostDto;
import com.martin.lezhin.controller.post.mapper.PostMapper;
import com.martin.lezhin.service.post.PostService;
import com.martin.lezhin.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostMapper postMapper;
    private final UserService userService;

    @GetMapping("{id}")
    public ResponseEntity getPost(@PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body(postMapper.entityToDto(postService.getPost(id)));
    }

    @PostMapping
    public ResponseEntity postPost(@RequestBody PostDto postDto) {
        return ResponseEntity
                .ok()
                .body(postMapper.entityToDto(
                        postService.postPost(postMapper.dtoToEntity(postDto), userService.getUser(postDto.getUserId()))));
    }

    @PutMapping
    public ResponseEntity putPost(@RequestBody PostDto postDto) {
        return ResponseEntity
                .ok()
                .body(postMapper.entityToDto(
                        postService.putPost(postMapper.dtoToEntity(postDto))));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok().build();
    }
}
