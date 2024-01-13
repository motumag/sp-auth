package com.motuma.security.services;

import com.motuma.security.model.User;
import com.motuma.security.payload.AuthenticationRequest;
import com.motuma.security.payload.AuthenticationResponse;
import com.motuma.security.payload.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest registerRequest);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    void saveUserToken(User user, String jwtToken);
    void revokeAllUserTokens(User user);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
