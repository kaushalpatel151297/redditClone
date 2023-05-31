package com.redditclone.Controller;

import com.redditclone.DTO.PostDTO;
import com.redditclone.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@CrossOrigin(origins = "*")
public class PostController {
    
    @Autowired
    private PostService postService;
    
    
    @PostMapping("/create/{subId}")
    public ResponseEntity<PostDTO> creterPost(@RequestBody PostDTO postdto,@PathVariable("subId")Long subId)
    {
        PostDTO post = this.postService.createPost(postdto, subId);
        return new  ResponseEntity<PostDTO>(post,HttpStatus.CREATED);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getpostById(@PathVariable("postId")Long postId)
    {
        PostDTO post = this.postService.getPostCreate(postId);
        return new ResponseEntity<PostDTO>(post,HttpStatus.OK);
    }
    @GetMapping("/Getall")
    public ResponseEntity<List<PostDTO>> getAllPosts()
    {
        List<PostDTO> postDTOS = this.postService.GetAll();
        return new ResponseEntity<List<PostDTO>>(postDTOS,HttpStatus.OK);
    }
    @GetMapping("/getPostBySubreddit/{subId}")
    public ResponseEntity<List<PostDTO>> getPostBySubreddit(@PathVariable("subID")Long subId)
    {
        List<PostDTO> postBySubID = this.postService.getPostBySubID(subId);
        return new ResponseEntity<List<PostDTO>>(postBySubID,HttpStatus.OK);
    }
}
