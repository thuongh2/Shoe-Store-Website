//package com.example.businessapp.securiry;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import io.jsonwebtoken.security.SignatureException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.time.ZonedDateTime;
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Slf4j
//@Component
//public class TokenProvider {
//
//        @Value("${jwt.key}")
//        private String jwtSecret;
//
//        private Long jwtExpirationMinutes = 30L; //time expier for jwt
//
//        public String generate(Authentication authentication){
//                CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//
//                List<String> role = userDetails.getAuthorities()
//                        .stream().map(GrantedAuthority::getAuthority)
//                        .collect(Collectors.toList());
//
//                //error
//                byte[] signingKey = jwtSecret.getBytes();
//
//                return Jwts.builder()
//                        .setHeaderParam("typ", TOKEN_TYPE)
//                        .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS256)
//                        .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(jwtExpirationMinutes).toInstant()))
//                        .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
//                        .setId(UUID.randomUUID().toString())
//                        .setIssuer(TOKEN_ISSUER)
//                        .setAudience(TOKEN_AUDIENCE)
//                        .setSubject(userDetails.getUsername())
//                        .claim("role", role)
//                        .claim("preferred_username", userDetails.getUsername())
//                        .claim("name", userDetails.getName())
//                        .claim("email", userDetails.getEmail())
//                        .compact();
//        }
//
//        public Optional<Jws<Claims>> validationTokenAndGetJWT(String token){
//                try {
//                        // validation token
//                        byte[] signingKey = jwtSecret.getBytes();
//
//                        Jws<Claims> jws = Jwts.parserBuilder()
//                                .setSigningKey(signingKey)
//                                .build()
//                                .parseClaimsJws(token);
//
//                        return Optional.of(jws);
//                }catch (ExpiredJwtException exception) {
//                        log.error("Request to parse expired JWT : {} failed : {}", token, exception.getMessage());
//                } catch (UnsupportedJwtException exception) {
//                        log.error("Request to parse unsupported JWT : {} failed : {}", token, exception.getMessage());
//                } catch (MalformedJwtException exception) {
//                        log.error("Request to parse invalid JWT : {} failed : {}", token, exception.getMessage());
//                } catch (SignatureException exception) {
//                        log.error("Request to parse JWT with invalid signature : {} failed : {}", token, exception.getMessage());
//                } catch (IllegalArgumentException exception) {
//                        log.error("Request to parse empty or null JWT : {} failed : {}", token, exception.getMessage());
//                }
//                return Optional.empty();
//        }
//
//        public static final String TOKEN_TYPE = "JWT";
//        public static final String TOKEN_ISSUER = "business-api";
//        public static final String TOKEN_AUDIENCE = "business-app";
//}
