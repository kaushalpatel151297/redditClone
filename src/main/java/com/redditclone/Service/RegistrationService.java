package com.redditclone.Service;

import com.redditclone.DTO.RegisterRequest;
import com.redditclone.Exception.SpringBootApplicationException;
import com.redditclone.Model.NotificationEmail;
import com.redditclone.Model.User;
import com.redditclone.Model.VerficationToken;
import com.redditclone.Reposiotry.UserReposiotry;
import com.redditclone.Reposiotry.VerficationTokenReposiotry;
import io.jsonwebtoken.Jwt;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;


@Service
@Transactional
@AllArgsConstructor
public class RegistrationService {

    @Autowired
    private UserReposiotry userReposiotry;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private VerficationTokenReposiotry verificationtokenrepo;
    @Autowired
    private MailService mailService;

    public void signup(RegisterRequest register)
    {
        User user  = new User();
        user.setEmail(register.getEmail());
        user.setUsername(register.getUsername());
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);
        this.userReposiotry.save(user);

       String token =  genrateVerficationToken(user);
       mailService.sednMail(new NotificationEmail("Please Activate your Email Address!",user.getEmail(),"Thanks for the signing up with Reddit"+
               "please click here for the below the activate yout  activate!" + "http://localhost:8080/api/auth/accountVerification/" + token));

    }

    private String genrateVerficationToken(User user) {

       String token =UUID.randomUUID().toString();
        VerficationToken verification  = new VerficationToken();
        verification.setToken(token);
        verification.setUser(user);
        verificationtokenrepo.save(verification);
        return token;


    }

    public void verifyAccount(String token) {
        Optional<VerficationToken> verificationToken = verificationtokenrepo.findByToken(token);
        fetchUserAndEnable(verificationToken.orElseThrow(() -> new SpringBootApplicationException("Invalid Token")));
    }
    @Transactional
    private void fetchUserAndEnable(VerficationToken verficationToken) {
        String username = verficationToken.getUser().getUsername();
        User user = userReposiotry.findByUsername(username).orElseThrow(() -> new SpringBootApplicationException("User not found" + username));
        user.setEnabled(true);
        userReposiotry.save(user);
    }
//    @Transactional(readOnly = true)
//    public User getUser()
//    {
//        Jwt principal = (Jwt) SecurityContextHolder.
//                getContext().getAuthentication().getPrincipal();
//        this.userReposiotry.findByUsername(principal)
//                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getSubject()));
//    }

}
