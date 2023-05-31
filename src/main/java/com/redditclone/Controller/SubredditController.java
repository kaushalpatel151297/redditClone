package com.redditclone.Controller;

import com.redditclone.DTO.SubredditDTO;
import com.redditclone.Service.SubredditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.hibernate.query.results.Builders.entity;

@RestController
@RequestMapping("/api/subreddits")
public class SubredditController {

    @Autowired
    private SubredditService subservice;

    @PostMapping("/create")
    public ResponseEntity<SubredditDTO> craeteSubreddit(@RequestBody SubredditDTO subredditdto) throws Exception
    {
        SubredditDTO subreddit = this.subservice.createSubreddit(subredditdto);
        return new  ResponseEntity<SubredditDTO>(subreddit,HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<SubredditDTO>> getAllSubreddits()
    {
        List<SubredditDTO> allSubreddit = this.subservice.getAllSubreddit();
        return new ResponseEntity<List<SubredditDTO>>(allSubreddit,HttpStatus.OK);
    }
    @GetMapping("/{subId}")
    public ResponseEntity<SubredditDTO> getAllSubreddits(@PathVariable("subId") Long subId)
    {
        SubredditDTO subrediById = this.subservice.getSubrediById(subId);

        return new  ResponseEntity<SubredditDTO>(subrediById,HttpStatus.OK);
    }
}


