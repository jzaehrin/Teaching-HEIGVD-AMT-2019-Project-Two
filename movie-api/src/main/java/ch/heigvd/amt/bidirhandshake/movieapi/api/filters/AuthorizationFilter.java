package ch.heigvd.amt.bidirhandshake.movieapi.api.filters;

import ch.heigvd.amt.bidirhandshake.movieapi.api.exceptions.ApiError;
import ch.heigvd.amt.bidirhandshake.movieapi.entities.User;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(1)
public class AuthorizationFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException { ;

        User.Role role = User.Role.valueOf((String) request.getAttribute("role"));

        if (!role.equals(User.Role.admin)) {
            FilterUtils.writeError(response, new ApiError(HttpStatus.UNAUTHORIZED, "You need to be admin !"));
            return;
        }

        chain.doFilter(request, response);
    }
}

