package com.martin.lezhin.entity.post;

import com.martin.lezhin.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void updatePostValue(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
    }

    public void setWriteUser(User user) {
        this.user = user;
    }
}
