package com.redditclone.Reposiotry;

import com.redditclone.DTO.CommentsDTO;
import com.redditclone.Model.Comment;
import com.redditclone.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentReposiotry extends JpaRepository<Comment,Long> {
    List<CommentsDTO> findbyPost(Post post);

    List<CommentsDTO> findAllByUser(String username1);
}
