package com.redditclone.Reposiotry;

import com.redditclone.Model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteReposiotry extends JpaRepository<Vote,Long> {
}
