package com.redditclone.Controller;

import com.redditclone.DTO.VoteDTO;
import com.redditclone.Service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/votes/")
@AllArgsConstructor
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping("/create")
    public ResponseEntity<VoteDTO> createVote(@RequestBody VoteDTO voteDto)
    {
        VoteDTO vote = this.voteService.createVote(voteDto);
        return new ResponseEntity<VoteDTO>(vote, HttpStatus.CREATED);
    }
}
