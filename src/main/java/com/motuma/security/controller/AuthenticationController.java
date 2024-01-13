package com.motuma.security.controller;

import com.motuma.security.exception.DuplicateOrAlreadyExistException;
import com.motuma.security.payload.AuthenticationRequest;
import com.motuma.security.payload.AuthenticationResponse;
import com.motuma.security.payload.RegisterRequest;
import com.motuma.security.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegisterRequest request){
        try {
            return ResponseEntity.ok(authenticationService.register(request));
        }catch (DuplicateOrAlreadyExistException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    AuthenticationResponse.builder()
                            .timeStamp(LocalDateTime.now().toString())
                            .message(e.getMessage())
                            .status(HttpStatus.CONFLICT)
                            .statusCode(HttpStatus.CONFLICT.value())
                            .build()
            );
        }
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthenticationRequest request){
        try {
            return ResponseEntity.ok(authenticationService.authenticate(request));
        }catch (DuplicateOrAlreadyExistException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    AuthenticationResponse.builder()
                            .timeStamp(LocalDateTime.now().toString())
                            .message(e.getMessage())
                            .status(HttpStatus.FORBIDDEN)
                            .statusCode(HttpStatus.FORBIDDEN.value())
                            .build()
            );
        }
    }
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response);
    }

}
