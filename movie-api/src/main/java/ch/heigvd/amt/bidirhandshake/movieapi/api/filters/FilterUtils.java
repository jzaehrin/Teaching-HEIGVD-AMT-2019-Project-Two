package ch.heigvd.amt.bidirhandshake.movieapi.api.filters;

import ch.heigvd.amt.bidirhandshake.movieapi.api.exceptions.ApiError;
import ch.heigvd.amt.bidirhandshake.movieapi.dto.Error;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterUtils {
    public static void writeError(HttpServletResponse response, ApiError apiError) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        response.setStatus(apiError.getStatus().value());
        response.getWriter().write(mapper.writeValueAsString(new Error().error(apiError.getMessage())));
        response.getWriter().flush();
    }
}
