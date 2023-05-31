package com.redditclone.Reposiotry;

import com.redditclone.DTO.PostDTO;
import com.redditclone.Model.Post;
import com.redditclone.Model.Subredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostReposiotry extends JpaRepository<Post,Long>{

    public List<Post> findyBySubreddit(Subredit subredit);
}
