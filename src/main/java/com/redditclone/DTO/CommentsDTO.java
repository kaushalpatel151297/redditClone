package com.redditclone.DTO;

import com.redditclone.Model.Post;
import com.redditclone.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentsDTO {

    private String text;

    private Date createDate;

    private User user;

    private Post post;
}
