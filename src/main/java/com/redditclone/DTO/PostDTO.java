package com.redditclone.DTO;

import com.redditclone.Model.Subredit;
import com.redditclone.Model.User;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDTO {

    private Long postId;

    private String postName;

    private String url;
    private Instant createDate;
    private Integer voteCount = 0;
    private String description;
    private Subredit subredit;
    private User user;


}
