package com.blogapp12.controller;


import com.blogapp12.entity.User;
import com.blogapp12.payload.LoginDto;
import com.blogapp12.payload.UserDto;
import com.blogapp12.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/auth/api")
@RestController
public class UserAuthController
{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    //http://localhost:8080/auth/sign-up
    @PostMapping("/sign-up")
    public ResponseEntity<?>  signUpFeature(@RequestBody UserDto userDto)
    {


        if(userRepository.existsByUserName(userDto.getUserName()))
        {
            return  new ResponseEntity<>("This UserName is Already Present", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(userRepository.existsByEmail(userDto.getEmail()))
        {
            return  new ResponseEntity<>("This email is Already Present", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        User user=new User();
        user.setUserName(userDto.getUserName());
        user.setName(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        User save = userRepository.save(user);

        return new ResponseEntity<>("User Registration is Sucessful",HttpStatus.OK);

    }

        @PostMapping("/sign-in")
        public ResponseEntity<?> Login(@RequestBody LoginDto loginDto)
        {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword());
//
//            authenticationManager: This is an instance of AuthenticationManager, provided by Spring Security. It's responsible for authenticating the user. The authenticate() method is the core method provided by AuthenticationManager to perform authentication.
//
//            authenticate(usernamePasswordAuthenticationToken): This method takes an Authentication object as an argument, typically in the form of UsernamePasswordAuthenticationToken, and attempts to authenticate the user based on the credentials provided in that token.
//
//                Authentication object: The authenticate() method returns an Authentication object if the authentication is successful. This object contains details about the authenticated principal (user) and, optionally, their granted authorities (roles).
//
//                So, in summary, this line of code triggers the authentication process using the provided AuthenticationManager. If the credentials are valid, it returns an Authentication object, indicating a successful authentication. Otherwise, it may throw an exception if authentication fails.

            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

//            The line SecurityContextHolder.getContext().setAuthentication(authenticate); sets the authenticated Authentication object (authenticate) into the SecurityContextHolder.
//
//                Here's what it does:
//
//            SecurityContextHolder: This is a central class in Spring Security that stores the details of the currently authenticated principal (user) of the application. It provides access to the security context.
//
//                getContext(): This method retrieves the current security context associated with the executing thread.
//
//            setAuthentication(authenticate): This method sets the provided Authentication object (authenticate) as the authenticated principal in the security context. This means that the user represented by the authenticate object is now considered authenticated within the application.
//
//                By setting the authenticated Authentication object in the SecurityContextHolder, you're making the authentication information available throughout the application. This is important because other parts of your application, such as authorization logic, might rely on the current authenticated user's information stored in the security context.
//
                 SecurityContextHolder.getContext().setAuthentication(authenticate);

            return new ResponseEntity<>("user is create",HttpStatus.OK);
        }



}
