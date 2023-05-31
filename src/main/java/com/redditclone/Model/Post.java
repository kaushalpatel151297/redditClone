package com.redditclone.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String postName;

    private String url;
    @Lob
    private String description;

    private Integer voteCount = 0;
    @CreatedDate
    private Instant createDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subRedId",referencedColumnName = "subId")
    private Subredit subredit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERID",referencedColumnName = "userId")
    private User user;
}

