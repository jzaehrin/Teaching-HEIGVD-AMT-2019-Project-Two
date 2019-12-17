package ch.heigvd.amt.bidirhandshake.authapi.api.utils;

import ch.heigvd.amt.bidirhandshake.authapi.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JWTHelper {
    private final static String secret = "J9Z9crFXwy5tGJWfKvqGtP7nm71p4CHlT5aCkQLNqI2cHNncdmu36S3QeSPi1IJB";

    private final static Algorithm algorithm = Algorithm.HMAC256(secret);

    public static String createFor(User user) {
        return JWT.create()
                .withIssuer("auth_api")
                .withExpiresAt(new Date(new Date().getTime() + (1000 * 60 * 60 * 24)))
                .withNotBefore(new Date())
                .withClaim("user_id", user.getId())
                .withClaim("role", user.getRole().name())
                .sign(algorithm);
    }

    public static DecodedJWT verify(String token) {
        JWTVerifier verifier = null;
        verifier = JWT.require(algorithm)
                .withIssuer("auth_api")
                .build();

        try {
            return verifier.verify(token);
        } catch (JWTVerificationException exception){
            return null;
        }
    }
}
