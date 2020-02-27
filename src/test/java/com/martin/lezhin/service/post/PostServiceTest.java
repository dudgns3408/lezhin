package com.martin.lezhin.service.post;

import com.martin.lezhin.entity.post.Post;
import com.martin.lezhin.entity.user.User;
import com.martin.lezhin.exception.HomeworkException;
import com.martin.lezhin.repositories.post.PostRepository;
import com.martin.lezhin.service.user.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;

    @AfterEach
    void cleanup() {
        postRepository.deleteAll();
    }

    @Test
    void getPost() {
        Post post = postService.postPost(
                Post.builder()
                        .title("title1")
                        .content("content1").build()
                , userService.getUser(2L));

        assertAll(
                () -> assertEquals(post.getId(), postService.getPost(post.getId()).getId())
                , () -> assertEquals("title1", postService.getPost(post.getId()).getTitle())
                , () -> assertEquals("content1", postService.getPost(post.getId()).getContent())
                , () -> assertEquals(2, postService.getPost(post.getId()).getUser().getId())
        );
    }

    @Test
    void deletePost() {
        Post post = postService.postPost(
                Post.builder()
                        .title("title2")
                        .content("content2").build()
                , userService.getUser(2L));

        assertEquals(post.getId(), postService.getPost(post.getId()).getId());

        postService.deletePost(post.getId());

        assertThrows(HomeworkException.class, () -> postService.getPost(post.getId()));
    }

    @Test
    void postPost() {
        Post post = postService.postPost(
                Post.builder()
                        .title("title4")
                        .content("content4").build()
                , userService.getUser(3L));

        assertAll(
                () -> assertEquals(post.getId(), postService.getPost(post.getId()).getId())
                , () -> assertEquals("title4", postService.getPost(post.getId()).getTitle())
                , () -> assertEquals("content4", postService.getPost(post.getId()).getContent())
                , () -> assertEquals(post.getId(), postService.getPost(post.getId()).getUser().getId())
        );
    }

    @Test
    void putPost() {
        User user = userService.getUser(2L);
        Post post = postService.postPost(
                Post.builder()
                        .title("title2")
                        .content("content2").build()
                , user);

        Post updatePost = postService.putPost(
                Post.builder()
                        .id(post.getId())
                        .title("title22")
                        .content("content22")
                        .user(user)
                .build()
        );

        assertAll(
                () -> assertEquals(updatePost.getTitle(), postService.getPost(post.getId()).getTitle())
                , () -> assertEquals(updatePost.getContent(), postService.getPost(post.getId()).getContent())
                , () -> assertEquals(2, postService.getPost(post.getId()).getUser().getId())
        );
    }
}