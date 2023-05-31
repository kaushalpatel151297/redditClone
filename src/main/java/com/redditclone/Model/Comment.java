package com.redditclone.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Comment {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long commnentId;

private String text;

private Date createDate;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "POSTID",referencedColumnName = "postId")
    private Post post;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERID",referencedColumnName = "userId")
    private User user;


}
