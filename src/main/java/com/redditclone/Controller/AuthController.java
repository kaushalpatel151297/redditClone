package com.redditclone.Controller;
import com.redditclone.DTO.JWTResponse;
import com.redditclone.DTO.JWTTokenHelper;
import com.redditclone.DTO.Login;
import com.redditclone.DTO.RegisterRequest;
import com.redditclone.Service.CustomUserDetailsService;
import com.redditclone.Service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JWTTokenHelper jwthelper;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<String> Sign(@RequestBody RegisterRequest register)
    {
        registrationService.signup(register);
        return new ResponseEntity<>("User Registration Successfully", HttpStatus.OK);
    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        registrationService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully", HttpStatus.OK);
    }

    @GetMapping("/k")
    public String some()
    {
        return "sometings";
    }
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody Login login) throws Exception {

        System.out.println("Inside Controller");
        System.out.println(login);
        try {

            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));


        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }catch (BadCredentialsException e)
        {
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }


        //fine area..
        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(login.getUsername());

        String token = this.jwthelper.generateToken(userDetails);
        System.out.println("JWT " + token);

        //{"token":"value"}

        return ResponseEntity.ok(new JWTResponse(token));

    }

}
