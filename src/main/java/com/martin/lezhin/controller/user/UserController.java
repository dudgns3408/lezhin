package com.martin.lezhin.controller.user;

import com.martin.lezhin.controller.post.mapper.PostMapper;
import com.martin.lezhin.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PostMapper postMapper;

    @GetMapping("{id}/followers")
    public ResponseEntity getUserFollowers(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getFollowers(id));
    }

    @GetMapping("{id}/following")
    public ResponseEntity getUserFollowing(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getFollowing(id));
    }

    @GetMapping("{id}/posts")
    public ResponseEntity getUserPosts(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getPosts(id));
    }

    @GetMapping("{id}/following/posts")
    public ResponseEntity getUserFollowingPosts(@PathVariable Long id) {
        return ResponseEntity.ok().body(postMapper.entityListToDtoList(userService.getFollowingsPost(id)));
    }

    @PostMapping("{id}/follow/{followId}")
    public ResponseEntity followUser(@PathVariable Long id, @PathVariable Long followId) {
        return ResponseEntity.ok().body(userService.followUser(id, followId));
    }

    @PostMapping("{id}/unfollow/{unfollowId}")
    public ResponseEntity unfollowUser(@PathVariable Long id, @PathVariable Long unfollowId) {
        return ResponseEntity.ok().body(userService.unFollowUser(id, unfollowId));
    }
}
