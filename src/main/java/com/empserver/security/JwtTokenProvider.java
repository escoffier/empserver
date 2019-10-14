package com.empserver.security;

import com.empserver.config.AppProperties;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Profile("with-security")
@Component
public class JwtTokenProvider {

    private static Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

//    private long validityInMilliseconds = 3600000;
//
//    private String secretKey = "secret";

    private AppProperties appProperties;

    public JwtTokenProvider(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String createToken(String userName, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userName);
        claims.put("roles", roles);
        Date now = new Date();
        Date validity = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, appProperties.getAuth().getTokenSecret())
                .compact();
    }

    public String createToken(String userName) {
        Claims claims = Jwts.claims().setSubject(userName);
        //claims.put("roles", roles);
        Date now = new Date();
        Date validity = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, appProperties.getAuth().getTokenSecret())
                .compact();
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }
}
