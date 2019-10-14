package com.empserver.controller;

import com.empserver.mapper.UserMapper;
import com.empserver.model.AuthProvider;
import com.empserver.model.User;
import com.empserver.security.JwtTokenProvider;
import com.empserver.security.LoginRequest;
import com.empserver.security.SignUpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Profile("with-security")
@RestController
public class AuthController {

    private static Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/signin")
    public Map<String, String> signin(@RequestBody  LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Map<String, String> loginResponse = new HashMap<>();
        loginResponse.put("email", loginRequest.getEmail());
        String token = tokenProvider.createToken(loginRequest.getEmail());
        loginResponse.put("token", token);
        return loginResponse;
    }

    @PostMapping("/signup")
    public Long registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        // Creating user's account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insertUser(user);

        return user.getId();
    }
}
