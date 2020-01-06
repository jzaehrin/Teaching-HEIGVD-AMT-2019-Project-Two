package ch.heigvd.amt.bidirhandshake.movieapi.api.filters;

import ch.heigvd.amt.bidirhandshake.movieapi.api.exceptions.ApiError;
import ch.heigvd.amt.bidirhandshake.movieapi.api.utils.JWTHelper;
import ch.heigvd.amt.bidirhandshake.movieapi.dto.Error;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthentificationFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader("Authorization");

        if(token == null) {
            FilterUtils.writeError(response, new ApiError(HttpStatus.UNAUTHORIZED, "Missing Authorized headers"));
            return;
        }

        DecodedJWT jwtDecoded = JWTHelper.verify(token);

        if(jwtDecoded == null) {
            FilterUtils.writeError(response, new ApiError(HttpStatus.UNAUTHORIZED, "Invalid token"));
            return;
        }

        request.setAttribute("userId", jwtDecoded.getClaim("user_id").asInt());
        request.setAttribute("role", jwtDecoded.getClaim("role").asString());

        chain.doFilter(request, response);
    }


}

