package ch.heigvd.amt.bidirhandshake.movieapi.api.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;


public class JWTHelper {
    private final static String secret = "J9Z9crFXwy5tGJWfKvqGtP7nm71p4CHlT5aCkQLNqI2cHNncdmu36S3QeSPi1IJB";

    private final static Algorithm algorithm = Algorithm.HMAC256(secret);

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
