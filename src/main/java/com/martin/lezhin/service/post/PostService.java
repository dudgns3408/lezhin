package com.martin.lezhin.service.post;

import com.martin.lezhin.entity.post.Post;
import com.martin.lezhin.entity.user.User;
import com.martin.lezhin.exception.HomeworkException;
import com.martin.lezhin.repositories.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post getPost(Long id) {
        return postRepository.findWithUserById(id)
                .orElseThrow(() -> new HomeworkException("찾는 포스트가 없습니다. id : " + id, HttpStatus.BAD_REQUEST));
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public Post postPost(Post post, User user) {
        post.setWriteUser(user);
        return postRepository.save(post);
    }

    public Post putPost(Post post) {
        Post existPost = postRepository.findById(post.getId())
                .orElseThrow(() -> new HomeworkException("찾는 포스트가 없습니다. id : " + post.getId(), HttpStatus.BAD_REQUEST));

        existPost.updatePostValue(post);

        return postRepository.save(existPost);
    }
}
