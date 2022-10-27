package com.naumstore.security.jwt;

import com.naumstore.configuration.JwtSecurityConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.jsonwebtoken.Claims.SUBJECT;

@Component
@RequiredArgsConstructor
public class JwtTokenHelper {

    public static final String ROLES = "roles";

    public static final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS512;

    public static final String JWT = "JWT";

    private final JwtSecurityConfig jwtSecurityConfig;

    //-------------------------------------------------------1 generateToken
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(SUBJECT, userDetails.getUsername());
        claims.put(ROLES, getEncryptedRoles(userDetails));
        return generateToken(claims);
    }

    //1.1 generateToken
    private List<String> getEncryptedRoles(UserDetails userDetails) {
        return userDetails.getAuthorities().
                stream()
                .map(GrantedAuthority::getAuthority)
                .map(s -> s.replace("ROLE_", ""))
                .collect(Collectors.toList());
    }

    //1.2 generateToken + 2 generateToken
    private String generateToken(Map<String, Object> claims) {

        final Date date = new Date();

        return Jwts
                .builder()
                .setHeader(generateJWTHeaders())
                .setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(generateExpirationDate(date))
                .signWith(ALGORITHM, jwtSecurityConfig.getSecret())
                .compact();
    }

    //1.2.1 generateToken
    private Map<String, Object> generateJWTHeaders() {
        Map<String, Object> jwtHeaders = new LinkedHashMap<>();
        jwtHeaders.put("typ", JWT);
        jwtHeaders.put("alg", ALGORITHM.getValue());

        return jwtHeaders;
    }

    //1.2.2 generateToken
    private Date generateExpirationDate(Date date) {
        Instant instant = date.toInstant();
        return Date.from(instant.plusSeconds(jwtSecurityConfig.getExpiration()));
    }

    //-------------------------------------------------------2 refreshToken
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            //log???
            refreshedToken = null;
        }
        return refreshedToken;
    }

    //2.1 refreshedToken + 3 canRefreshToken + 4 isValidToken
    public Claims getClaimsFromToken(String token) {
        return Jwts
                .parser()
                .setSigningKey(jwtSecurityConfig.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    //-------------------------------------------------------3 canRefreshToken
    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Claims claims = getClaimsFromToken(token);

        return (claims.getIssuedAt().before(lastPasswordReset))
                && !(claims.getExpiration().before(lastPasswordReset));
    }

    //-------------------------------------------------------4 isValidToken
    public boolean isValidToken(String token, UserDetails userDetails) {
        final String username = getClaimsFromToken(token).getSubject();
        return username.equals(userDetails.getUsername());
    }
}
