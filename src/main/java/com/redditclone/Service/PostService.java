package com.redditclone.Service;

import com.redditclone.DTO.PostDTO;
import com.redditclone.DTO.SubredditDTO;
import com.redditclone.Model.Post;
import com.redditclone.Model.Subredit;
import com.redditclone.Model.User;
import com.redditclone.Reposiotry.PostReposiotry;
import com.redditclone.Reposiotry.SubredditReposiotry;
import com.redditclone.Reposiotry.UserReposiotry;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostReposiotry postRepo;
    @Autowired
    private UserReposiotry userRepository;
    @Autowired
    private SubredditReposiotry subredditRepo;
    @Autowired
    private ModelMapper modelmapper;


    public PostDTO createPost(PostDTO postdto,Long subId)
    {
        Subredit subredit = this.subredditRepo.findById(subId).orElseThrow(()->new IllegalStateException("Could Not Find the SubReddit ID for post"));
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

      String  username = userDetails.getUsername();
        User user = this.userRepository.findByUsername(username).orElseThrow(()-> new IllegalStateException("Could not find the Username!"));

        Post post = this.modelmapper.map(postdto, Post.class);
        post.setSubredit(subredit);
        post.setUser(user);
        Post save = this.postRepo.save(post);

        return this.modelmapper.map(save,PostDTO.class);
    }
    public PostDTO getPostCreate(Long postId)
    {
        Post post;
        post = this.postRepo.findById(postId).orElseThrow(()-> new IllegalStateException("Could not find Post ID!"));
        System.out.println("*************"+post);
        return this.modelmapper.map(post,PostDTO.class);
    }
    public List<PostDTO> GetAll()
    {
        List<Post> post = this.postRepo.findAll();
        List<PostDTO> collect = post.stream().map(cat -> modelmapper.map(cat, PostDTO.class)).collect(Collectors.toList());
        return collect;
    }
    public List<PostDTO> getPostBySubID(Long subId)
    {
        Subredit subredit = this.subredditRepo.findById(subId).orElseThrow(() -> new IllegalStateException("Subreddit ID could not there!"));
        List<Post> posts = this.postRepo.findyBySubreddit(subredit);
        return posts.stream().map(post -> modelmapper.map(post, PostDTO.class)).collect(Collectors.toList());
    }

}
