package com.redditclone.Service;

import com.redditclone.DTO.VoteDTO;
import com.redditclone.Exception.ResourceNotFoundException;
import com.redditclone.Model.Post;
import com.redditclone.Model.User;
import com.redditclone.Model.Vote;
import com.redditclone.Model.VoteType;
import com.redditclone.Reposiotry.PostReposiotry;
import com.redditclone.Reposiotry.UserReposiotry;
import com.redditclone.Reposiotry.VoteReposiotry;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoteService {

    @Autowired
    private VoteReposiotry voteReposiotry;
    @Autowired
    private PostReposiotry postReposiotry;
    @Autowired
    private UserDetails userDetails;
    @Autowired
    private UserReposiotry userReposiotry;
    @Autowired
    private ModelMapper modelampper;

    public VoteDTO createVote(VoteDTO voteDTO) {
        Post post = this.postReposiotry.findById(voteDTO.getPostId()).orElseThrow(()->
                new ResourceNotFoundException("postID is not Found","postId",voteDTO.getPostId().toString()));

        String username = this.userDetails.getUsername();
        User user = this.userReposiotry.findByUsername(username).orElseThrow(()->
                new ResourceNotFoundException("UserID is not Found","userId",voteDTO.getUserId().toString()));
        Vote vote = this.modelampper.map(voteDTO, Vote.class);

        vote.setUser(user);
//        if (user.isPresent())

        if (VoteType.UPVOTE.equals(voteDTO.getVotetype()))
        {
            post.setVoteCount(post.getVoteCount()+1);
            vote.setPost(post);
        }
        else {
            post.setVoteCount(post.getVoteCount()-1);
            vote.setPost(post);
        }
        Vote save = this.voteReposiotry.save(vote);
        VoteDTO votedtofinal = this.modelampper.map(save, VoteDTO.class);
        postReposiotry.save(post);
        return votedtofinal;
    }

}
