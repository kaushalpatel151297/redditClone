package com.redditclone.Reposiotry;

import com.redditclone.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserReposiotry extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
