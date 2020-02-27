package com.martin.lezhin.service.user;

import com.martin.lezhin.entity.post.Post;
import com.martin.lezhin.entity.user.User;
import com.martin.lezhin.exception.HomeworkException;
import com.martin.lezhin.repositories.post.PostRepository;
import com.martin.lezhin.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public List<String> getFollowers(Long id) {
        return userRepository.findWithFollowersById(id)
                .orElseThrow(() -> new HomeworkException("찾는 유저가 없습니다. id : " + id, HttpStatus.BAD_REQUEST))
                .getFollowers()
                .stream()
                .map(User::getName)
                .collect(toList());
    }

    public List<String> getFollowing(Long id) {
        return userRepository.findWithFollowingById(id)
                .orElseThrow(() -> new HomeworkException("찾는 유저가 없습니다. id : " + id, HttpStatus.BAD_REQUEST))
                .getFollowing()
                .stream()
                .map(User::getName)
                .collect(toList());
    }

    public List<Post> getPosts(Long id) {
        return userRepository.findWithPostsById(id)
                .orElseThrow(() -> new HomeworkException("찾는 유저가 없습니다. id : " + id, HttpStatus.BAD_REQUEST))
                .getPosts();
    }

    @Transactional
    public List<Post> getFollowingsPost(Long id) {
        List<User> followingUsers = userRepository.findWithFollowingById(id)
                .orElseThrow(() -> new HomeworkException("찾는 유저가 없습니다. id : " + id, HttpStatus.BAD_REQUEST))
                .getFollowing();

        return postRepository.findAllByUserIn(followingUsers);
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new HomeworkException("찾는 유저가 없습니다. id : " + id, HttpStatus.BAD_REQUEST));
    }

    @Transactional
    public boolean followUser(Long id, Long followId) {
        User user = userRepository.findWithFollowingById(id).orElseThrow(() -> new HomeworkException("찾는 유저가 없습니다. id : " + id, HttpStatus.BAD_REQUEST));
        User followUser = userRepository.findWithFollowersById(followId).orElseThrow(() -> new HomeworkException("찾는 유저가 없습니다. id : " + followId, HttpStatus.BAD_REQUEST));

        return user.addFollowing(followUser);
    }

    @Transactional
    public boolean unFollowUser(Long id, Long unfollowId) {
        User user = userRepository.findWithFollowingById(id).orElseThrow(() -> new HomeworkException("찾는 유저가 없습니다. id : " + id, HttpStatus.BAD_REQUEST));
        User unfollowUser = userRepository.findWithFollowersById(unfollowId).orElseThrow(() -> new HomeworkException("찾는 유저가 없습니다. id : " + unfollowId, HttpStatus.BAD_REQUEST));

        return user.unFollowing(unfollowUser);
    }
}
