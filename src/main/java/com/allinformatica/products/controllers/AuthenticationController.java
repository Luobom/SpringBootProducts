package com.allinformatica.products.controllers;

import com.allinformatica.products.dto.AuthenticationDTO;
import com.allinformatica.products.dto.LoginResponseDTO;
import com.allinformatica.products.dto.RegisterDTO;
import com.allinformatica.products.security.TokenService;
import com.allinformatica.products.models.UsersModel;
import com.allinformatica.products.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var userNamePasswd = new UsernamePasswordAuthenticationToken(data.login(), data.passwd());
        var auth = this.authenticationManager.authenticate(userNamePasswd);

        var token = tokenService.generateToken((UsersModel) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.userRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPasswd = new BCryptPasswordEncoder().encode(data.passwd());
        UsersModel newUser = new UsersModel(data.login(), encryptedPasswd, data.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

}
