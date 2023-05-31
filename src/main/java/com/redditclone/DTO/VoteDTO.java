package com.redditclone.DTO;

import com.redditclone.Model.VoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VoteDTO {


    private VoteType votetype;

    private Long postId;

    private Long userId;
}
