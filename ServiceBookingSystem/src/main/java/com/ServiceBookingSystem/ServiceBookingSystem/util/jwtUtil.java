package com.ServiceBookingSystem.ServiceBookingSystem.util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class jwtUtil {
    public static final String SECRET = "5367871dfc54cv6787we7dc74982dsf9df763de8h6k25666q335r5849";

    private  String createTocken(Map<String,Object> calims, String userName ){
        return Jwts.builder()
                .setClaims(calims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()*1000*60*30))
                .signWith(SignatureAlgorithm.HS256, getSignkey()).compact();
    }

    private Key getSignkey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateTocken(String userName){
        Map<String , Object> claims = new HashMap<>();
        return createTocken(claims,userName);
    }
    private Claims extractAllaClaims(String tocken){
        return Jwts
                .parser()
                .setSigningKey(getSignkey())
                .parseClaimsJws(tocken)
                .getBody();
    }

    public <T> T extractClaims(String token , Function<Claims,T> claimsResolver){
        final Claims claims = extractAllaClaims(token);
        return claimsResolver.apply(claims);
    }

    public Date extractExpiration (String tocken){
        return extractClaims(tocken, Claims::getExpiration);
    }

    public String extractUsername(String token){
        return extractClaims(token,Claims::getSubject);
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    public boolean validateToken(String token , UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
