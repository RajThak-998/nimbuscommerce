package com.nimbuscommerce.authservice.controller;


import com.nimbuscommerce.authservice.dto.LoginRequestDTO;
import com.nimbuscommerce.authservice.dto.LoginResponseDTO;
import com.nimbuscommerce.authservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Generate token for user login")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        Optional<String> tokenOpt = authService.authenticate(loginRequestDTO);
        if(tokenOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        String token = tokenOpt.get();
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @Operation(summary = "Validate Token")
    @GetMapping("/validate")
    public ResponseEntity<Void> validateToken(@RequestHeader("Authorization") String authHeader){
        //Authorization: Bearer <token>
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return authService.validateToken(authHeader.substring(7)) ?
                ResponseEntity.ok().build() :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
