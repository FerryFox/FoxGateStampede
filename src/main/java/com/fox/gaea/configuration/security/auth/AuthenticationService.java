package com.fox.gaea.configuration.security.auth;


import com.fox.gaea.configuration.security.SecuritySender;
import com.fox.gaea.configuration.security.exceptions.TokenCheckException;
import com.fox.gaea.configuration.security.jwt.JwtService;
import com.fox.gaea.configuration.security.user.User;
import com.fox.gaea.configuration.security.user.UserRepository;

import com.fox.gaea.configuration.security.exceptions.RefreshTokenMissingException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService
{
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;

    public AuthenticationResponse register(RegisterRequest request)
    {
        var user = User.builder()
                .firstname(request.getFirstname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .receiveNews(request.isReceiveNews())
                .build();


        User savedUser = userRepository.save(user);
        eventPublisher.publishEvent(new SecuritySender(this, savedUser));


        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request)
    {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse refreshToken(HttpServletRequest request) throws RuntimeException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String token;
        final String email;

        if (authHeader == null ||!authHeader.startsWith("Bearer "))
        {
            throw new RefreshTokenMissingException("Refresh token is missing");
        }

        token = authHeader.substring(7);

        email = jwtService.extractUsername(token);

        if (email != null)
        {
            var user = userRepository.findByEmail(email).orElseThrow();
            var jwtToken = jwtService.generateLongLiveToken(user);

            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
        else
        {
            throw new RefreshTokenMissingException("Refresh token is missing");
        }
    }

    public boolean checkToken(HttpServletRequest request) throws TokenCheckException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String token;
        final String email;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return false;
        }

        token = authHeader.substring(7);

        try {
            email = jwtService.extractUsername(token);
            if (email == null) {
                return false;
            }
            var user = userRepository.findByEmail(email).orElseThrow();
            return jwtService.isTokenValid(token, user);
        }
        catch (SignatureException ex) {
            throw new TokenCheckException("Invalid token signature detected.");
        }
        catch (ExpiredJwtException ex) {
            throw new TokenCheckException("Token has expired.");
        }
        catch (Exception ex) {
            throw new TokenCheckException("Token is invalid.");
        }
    }
}