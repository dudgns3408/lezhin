package com.martin.lezhin.entity.user;

import com.martin.lezhin.entity.common.AuditEntity;
import com.martin.lezhin.entity.post.Post;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class User extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;

    @ManyToMany
    @JoinTable(name = "UserRelations",
            joinColumns = { @JoinColumn(name = "followed_id") },
            inverseJoinColumns = { @JoinColumn(name = "follower_id") })
    private List<User> followers = new ArrayList<>();

    @ManyToMany(mappedBy = "followers")
    private List<User> following = new ArrayList<>();

    public boolean addFollowing(User user) {
        if (!this.following.contains(user)) {
            return this.following.add(user) && user.addFollowers(this);
        } else {
            return false;
        }
    }

    private boolean addFollowers(User user) {
        return this.followers.add(user);
    }

    public boolean unFollowing(User user) {
        return this.following.remove(user) && user.unFollowers(this);
    }

    public boolean unFollowers(User user) {
        return this.followers.remove(user);
    }
}
