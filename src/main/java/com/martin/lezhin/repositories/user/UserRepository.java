package com.martin.lezhin.repositories.user;

import com.martin.lezhin.entity.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = {"followers"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<User> findWithFollowersById(Long id);

    @EntityGraph(attributePaths = {"following"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<User> findWithFollowingById(Long id);

    @EntityGraph(attributePaths = {"posts"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<User> findWithPostsById(Long id);
}
