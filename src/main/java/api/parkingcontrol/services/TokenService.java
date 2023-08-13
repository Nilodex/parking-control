package api.parkingcontrol.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import api.parkingcontrol.models.UserModel;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;


    public String generateToken(UserModel userModel){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                .withIssuer("parking-control-app")
                .withSubject(userModel.getUsername())
                .withExpiresAt(genExpirationDate())
                .sign(algorithm);
            return token;
        } catch (JWTCreationException jwtCreationException) {
            throw new RuntimeException("Error while generating token " + jwtCreationException);
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                .withIssuer("parking-control-app")
                .build()
                .verify(token)
                .getSubject();
        } catch (JWTVerificationException jwtVerificationException) {
            return "";
        }
    }


    public Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
    
}
