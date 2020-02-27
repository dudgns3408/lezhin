package com.martin.lezhin.repositories.post;

import com.martin.lezhin.entity.post.Post;
import com.martin.lezhin.entity.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    @EntityGraph(attributePaths = {"user"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<Post> findWithUserById(Long id);

    List<Post> findAllByUserIn(List<User> users);
}
