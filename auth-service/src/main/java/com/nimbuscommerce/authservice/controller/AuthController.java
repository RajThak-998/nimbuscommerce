package com.nimbuscommerce.authservice.controller;


import com.nimbuscommerce.authservice.dto.LoginRequestDTO;
import com.nimbuscommerce.authservice.dto.LoginResponseDTO;
import com.nimbuscommerce.authservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @Operation(summary = "Generate token for user login")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        log.info("Login attempt for user: {}", loginRequestDTO.getEmail());
        Optional<String> tokenOpt = authService.authenticate(loginRequestDTO);

        if(tokenOpt.isEmpty()) {
            log.warn("Login failed for user: {}", loginRequestDTO.getEmail());
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
            log.warn("Invalid Authorization header");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return authService.validateToken(authHeader.substring(7)) ?
                ResponseEntity.ok().build() :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
