package com.redditclone.Reposiotry;

import com.redditclone.Model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.ref.Reference;
@Repository
public interface RefreshTokenReposiotry extends JpaRepository<RefreshToken,Long> {
}
