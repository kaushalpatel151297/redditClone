package com.redditclone.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class SubredditDTO {

    private Long subId;

    private String name;

    private String description;

    private Integer noofPosts;

    private Date createdTime;
}

