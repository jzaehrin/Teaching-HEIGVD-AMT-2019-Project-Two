package ch.heigvd.amt.bidirhandshake.movieapi.api.filters;

import ch.heigvd.amt.bidirhandshake.movieapi.api.exceptions.ApiError;
import ch.heigvd.amt.bidirhandshake.movieapi.entities.User;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Order(1)
public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        User.Role role = User.Role.valueOf((String) req.getAttribute("role"));

        if (!role.equals(User.Role.admin)) throw new ApiError(HttpStatus.UNAUTHORIZED, "You need to be admin !");

        chain.doFilter(request, response);
    }
}
