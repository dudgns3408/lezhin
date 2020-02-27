package com.martin.lezhin.service.user;

import com.martin.lezhin.entity.post.Post;
import com.martin.lezhin.service.post.PostService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @AfterEach
    void cleanUp() {
        userService.unFollowUser(1L, 2L);
        userService.unFollowUser(1L, 3L);
    }

    @Test
    void getFollowers() {
        assertEquals(0, userService.getFollowers(2L).size());

        userService.followUser(1L, 2L);

        assertAll(
                () -> assertEquals(1, userService.getFollowers(2L).size())
                , () -> assertEquals("user1", userService.getFollowers(2L).get(0))
        );
    }

    @Test
    void getFollowing() {
        assertEquals(0, userService.getFollowing(1L).size());

        userService.followUser(1L, 2L);

        assertAll(
                () -> assertEquals(1, userService.getFollowing(1L).size())
                , () -> assertEquals("user2", userService.getFollowing(1L).get(0))
        );
    }

    @Test
    void getPosts() {
        assertEquals(0, userService.getPosts(1L).size());

        postService.postPost(
                Post.builder()
                        .title("title1")
                        .content("content1").build()
                , userService.getUser(1L));
        postService.postPost(
                Post.builder()
                        .title("title2")
                        .content("content2").build()
                , userService.getUser(1L));

        assertAll(
                () -> assertEquals(2, userService.getPosts(1L).size())
                , () -> assertEquals("title1", userService.getPosts(1L).get(0).getTitle())
                , () -> assertEquals(1, userService.getPosts(1L).get(0).getUser().getId())
                , () -> assertEquals("title2", userService.getPosts(1L).get(1).getTitle())
                , () -> assertEquals(1, userService.getPosts(1L).get(1).getUser().getId())
        );
    }

    @Test
    void getFollowingsPost() {
        postService.postPost(
                Post.builder()
                        .title("title1")
                        .content("content1").build()
                , userService.getUser(2L));
        postService.postPost(
                Post.builder()
                        .title("title2")
                        .content("content2").build()
                , userService.getUser(2L));
        postService.postPost(
                Post.builder()
                        .title("title3")
                        .content("content3").build()
                , userService.getUser(2L));
        postService.postPost(
                Post.builder()
                        .title("title4")
                        .content("content4").build()
                , userService.getUser(3L));
        postService.postPost(
                Post.builder()
                        .title("title5")
                        .content("content5").build()
                , userService.getUser(3L));

        userService.followUser(1L, 2L);
        userService.followUser(1L, 3L);

        assertAll(
                () -> assertEquals(5, userService.getFollowingsPost(1L).size())
                , () -> assertEquals("title1", userService.getFollowingsPost(1L).get(0).getTitle())
        );

        userService.unFollowUser(1L, 3L);

        assertAll(
                () -> assertEquals(3, userService.getFollowingsPost(1L).size())
                , () -> assertEquals("title1", userService.getFollowingsPost(1L).get(0).getTitle())
        );


        userService.unFollowUser(1L, 2L);
        userService.followUser(1L, 3L);

        assertAll(
                () -> assertEquals(2, userService.getFollowingsPost(1L).size())
                , () -> assertEquals("title4", userService.getFollowingsPost(1L).get(0).getTitle())
        );
    }

    @Test
    void getUser() {
        assertAll(
                () -> assertEquals(1, userService.getUser(1L).getId())
                , () -> assertEquals("user1", userService.getUser(1L).getName())
        );
    }

    @Test
    void followUser() {
        assertAll(
                () -> assertEquals(0, userService.getFollowers(2L).size())
                , () -> assertEquals(0, userService.getFollowing(1L).size())
        );

        userService.followUser(1L, 2L);

        assertAll(
                () -> assertEquals(1, userService.getFollowers(2L).size())
                , () -> assertEquals("user1", userService.getFollowers(2L).get(0))
                , () -> assertEquals(1, userService.getFollowing(1L).size())
                , () -> assertEquals("user2", userService.getFollowing(1L).get(0))
        );
    }

    @Test
    void unFollowUser() {
        userService.followUser(1L, 2L);

        assertAll(
                () -> assertEquals(1, userService.getFollowers(2L).size())
                , () -> assertEquals("user1", userService.getFollowers(2L).get(0))
                , () -> assertEquals(1, userService.getFollowing(1L).size())
                , () -> assertEquals("user2", userService.getFollowing(1L).get(0))
        );

        userService.unFollowUser(1L, 2L);

        assertAll(
                () -> assertEquals(0, userService.getFollowers(2L).size())
                , () -> assertEquals(0, userService.getFollowing(1L).size())
        );
    }
}