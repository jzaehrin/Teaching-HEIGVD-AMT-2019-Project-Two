package ch.heigvd.amt.bidirhandshake.authapi.api.filters;

import ch.heigvd.amt.bidirhandshake.authapi.api.exceptions.ApiError;
import ch.heigvd.amt.bidirhandshake.authapi.api.utils.JWTHelper;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.annotations.Api;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Order(1)
public class AuthentificationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Test");

        HttpServletRequest req = (HttpServletRequest) request;

        String token = req.getHeader("Authorization");

        if(token == null) throw new ApiError(HttpStatus.UNAUTHORIZED, "Missing Authorized headers");

        DecodedJWT jwtDecoded = JWTHelper.verify(token);

        if(jwtDecoded == null) throw new ApiError(HttpStatus.UNAUTHORIZED, "Invalid Token");

        request.setAttribute("userId", jwtDecoded.getClaim("user_id").asLong());

        chain.doFilter(request, response);
    }
}

