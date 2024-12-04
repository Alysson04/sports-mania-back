package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginRequest;
import com.example.demo.model.Usuario;
import com.example.demo.service.AuthService;
import com.example.demo.service.UsuarioService;

@RestController
@RequestMapping("/api/auth/")
public class LoginController {

    @Autowired
    private AuthService authService;
    
    @SuppressWarnings("unused")
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("login")
    public String login(@RequestBody LoginRequest loginRequest) {
        Usuario usuario = authService.login(loginRequest.getUsername(), loginRequest.getPassword());

        if (usuario != null) {
            return authService.generateToken(usuario);
        }

        return "Invalid credentials";
    }

    @PostMapping("logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "Logged out successfully";
    }
}