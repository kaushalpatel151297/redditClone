package com.redditclone.Service;

import com.redditclone.DTO.CommentsDTO;
import com.redditclone.DTO.PostDTO;
import com.redditclone.Exception.ResourceNotFoundException;
import com.redditclone.Model.Comment;
import com.redditclone.Model.NotificationEmail;
import com.redditclone.Model.Post;
import com.redditclone.Model.User;
import com.redditclone.Reposiotry.CommentReposiotry;
import com.redditclone.Reposiotry.PostReposiotry;
import com.redditclone.Reposiotry.UserReposiotry;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentsService {

    @Autowired
    private CommentReposiotry commentReposiotry;

    @Autowired
    private PostReposiotry postrepository;
    @Autowired
    private UserDetails userDetails;

    @Autowired
    private ModelMapper modelmapper;

    @Autowired
    private UserReposiotry userReposiotry;

    @Autowired
    private  MailService mailService;

    public CommentsDTO saveComments(CommentsDTO commentDTO)
    {
       Post some= commentDTO.getPost();
        Post byId = this.postrepository.findById(some.getPostId()).orElseThrow(()->new ResourceNotFoundException("Post","PostId",some.getPostId().toString()));
        Comment map = modelmapper.map(commentDTO, Comment.class);
        map.setPost(byId);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String  username = userDetails.getUsername();
        User user = this.userReposiotry.findByUsername(username).orElseThrow(()-> new IllegalStateException("Could not find the Username!"));
        map.setUser(user);
        Comment save = this.commentReposiotry.save(map);
        CommentsDTO map1 = this.modelmapper.map(save, CommentsDTO.class);
        return map1;
    }
    public List<CommentsDTO> getAllCommentsBYyPost(Long postId)
    {

        Post post = this.postrepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("POst","PostID",postId.toString()));
        List<CommentsDTO> collect = this.commentReposiotry.findbyPost(post).stream()
                .map(cn -> modelmapper.map(cn, CommentsDTO.class)).collect(Collectors.toList());
        return collect;
    }
    private void SendMailNotification(String message,User user)
    {
        this.mailService.sednMail(new NotificationEmail(user.getUsername() +"Commented on your post",user.getEmail(),message));
    }
    public List<CommentsDTO> getCommentsByUser
            (String username)
    {
        String username1 = this.userDetails.getUsername();
        List<CommentsDTO> byUsername = commentReposiotry.findAllByUser(username1)
                .stream()
                .map(cm->this.modelmapper.map(cm,CommentsDTO.class)).collect(Collectors.toList());
        return byUsername;

    }



}
