package com.redditclone.Reposiotry;

import com.redditclone.Model.VerficationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerficationTokenReposiotry extends JpaRepository<VerficationToken,Long> {
    Optional<VerficationToken> findByToken(String token);
}
