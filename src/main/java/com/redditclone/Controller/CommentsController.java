package com.redditclone.Controller;

import com.redditclone.DTO.CommentsDTO;
import com.redditclone.DTO.PostDTO;
import com.redditclone.Model.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.redditclone.Service.CommentsService;

import java.util.List;


@RestController
@RequestMapping("/api/comments")
public class CommentsController {


    @Autowired
    private CommentsService commentService;

    @Autowired
    private ModelMapper modelmapper;

    public ResponseEntity<CommentsDTO> createCOmment(@RequestBody CommentsDTO commentsDTO) {

        CommentsDTO commentsDTO1 = this.commentService.saveComments(commentsDTO);
        return new ResponseEntity<CommentsDTO>(commentsDTO1, HttpStatus.CREATED);
    }

    @GetMapping("/api/comments/{postId}")
    public ResponseEntity<List<CommentsDTO>> getAllCommentByPost(@PathVariable("postId") Long postId) {

        List<CommentsDTO> allCommentsBYyPost = this.commentService.getAllCommentsBYyPost(postId);
        return new ResponseEntity<List<CommentsDTO>>(allCommentsBYyPost, HttpStatus.OK);
    }
    @GetMapping("/api/comments/username")
    public ResponseEntity<List<CommentsDTO>> getCommentsByUser(@PathVariable("username") String username)
    {
        List<CommentsDTO> commentsByUser = this.commentService.getCommentsByUser(username);
        return new ResponseEntity<List<CommentsDTO>>(commentsByUser, HttpStatus.OK);
    }
}

