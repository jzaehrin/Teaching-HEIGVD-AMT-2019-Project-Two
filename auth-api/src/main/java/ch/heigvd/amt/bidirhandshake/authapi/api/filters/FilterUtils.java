package ch.heigvd.amt.bidirhandshake.authapi.api.filters;

import ch.heigvd.amt.bidirhandshake.authapi.api.exceptions.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;
import ch.heigvd.amt.bidirhandshake.authapi.dto.Error;

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
