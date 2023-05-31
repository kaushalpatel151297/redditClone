package com.redditclone.Service;

import com.redditclone.Model.User;
import com.redditclone.Reposiotry.UserReposiotry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserReposiotry userReposiotry;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = userReposiotry.findByUsername(username).get();
        if (byUsername==null)
        {
            throw new UsernameNotFoundException("UserName NOT FOUND");
        }
        else {
            return new CustomUserDetails(byUsername);
        }

    }
}
