package com.motuma.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "EhXa6VnuQowDMMIzprnWuGSaBMMcQc6v";

    //    TODO: This is to get the username from the token and use in JwtAuthenticationFilter class to check if the user is in db
    public String extractUserEmailOrPhoneFromJwt(String tokenFromJwtAuthFilter) {
//        Here now we extracted the username from the token
        return extractSingleClaimFromAllClaim(tokenFromJwtAuthFilter, Claims::getSubject);
    }

    //    TODO: Extract single claim after extracting the all claim. Use the allClaim method.
    public <T> T extractSingleClaimFromAllClaim(String tokenFromJwtAuthFilter, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(tokenFromJwtAuthFilter);
        return claimsResolver.apply(claims);
    }

    //    TODO: Extract all the claims of the token from the request.
    public Claims extractAllClaims(String tokenFromJwtAuthFilter) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(tokenFromJwtAuthFilter)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

//    TODO: Now generate a token for login and other stuffs you need
    public String generateToken(Map<String, Objects> extraClaimsCreated, UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(extraClaimsCreated)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
//    TODO: To generate token only with user details we can use like the bellow block of code
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }
//    TODO: Check token validation[For this we need method isTokenExpired,extractExpiration to implement ]
    public boolean isTokenValid(String tokenFromJwtAuthFilter, UserDetails userDetails){
        final String userNameFromToken= extractUserEmailOrPhoneFromJwt(tokenFromJwtAuthFilter);
        return ((userNameFromToken.equals(userDetails.getUsername()))&& !isTokenExpired(tokenFromJwtAuthFilter));
    }

    private boolean isTokenExpired(String tokenFromJwtAuthFilter) {
        return  extractExpiration(tokenFromJwtAuthFilter).before(new Date());
    }

    private Date extractExpiration(String tokenFromJwtAuthFilter) {
        return extractSingleClaimFromAllClaim(tokenFromJwtAuthFilter,Claims::getExpiration);
    }
}
