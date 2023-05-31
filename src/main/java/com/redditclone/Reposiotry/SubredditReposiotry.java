package com.redditclone.Reposiotry;

import com.redditclone.Model.Subredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubredditReposiotry extends JpaRepository<Subredit,Long> {
}
