package com.fox.gaea.configuration.security.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Map;
import java.util.function.Function;

public interface IJwtService
{
     String extractUsername(String token);
     <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
     String generateToken(UserDetails userDetails);
     String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);
     String generateLongLiveToken(UserDetails userDetails);
     boolean isTokenValid(String token, UserDetails userDetails);
}