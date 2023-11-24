package com.example.restapi.service;

import com.example.restapi.DTO.CustomerDTO;
import com.example.restapi.jwt.JwtAuthentication;
import com.example.restapi.jwt.JwtProvider;
import com.example.restapi.jwt.JwtRequest;
import com.example.restapi.jwt.JwtResponse;
import com.example.restapi.model.Customer;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomerService customerService;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final JwtProvider jwtProvider;

    public JwtResponse login(@NonNull JwtRequest authRequest) throws AuthException {
        final CustomerDTO customerDTO = customerService.getByEmail(authRequest.getEmail())
                .orElseThrow(() -> new AuthException("Customer not found"));

        if (customerService.checkPassword(customerDTO, authRequest.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(customerDTO);
            final String refreshToken = jwtProvider.generateRefreshToken(customerDTO);
            refreshStorage.put(customerDTO.getEmail(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new AuthException("Incorrect password");
        }
    }

    public JwtResponse getAccessToken(@NonNull String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String email = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(email);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final CustomerDTO customerDTO = customerService.getByEmail(email)
                        .orElseThrow(() -> new AuthException("Customer not found"));
                final String accessToken = jwtProvider.generateAccessToken(customerDTO);
                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }

    public JwtResponse refresh(@NonNull String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String email = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(email);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final CustomerDTO customerDTO = customerService.getByEmail(email)
                        .orElseThrow(() -> new AuthException("Customer not found"));
                final String accessToken = jwtProvider.generateAccessToken(customerDTO);
                final String newRefreshToken = jwtProvider.generateRefreshToken(customerDTO);
                refreshStorage.put(customerDTO.getEmail(), newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Invalid JWT token");
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
     }
}
