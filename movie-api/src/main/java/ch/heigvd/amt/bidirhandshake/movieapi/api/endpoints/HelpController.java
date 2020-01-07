package ch.heigvd.amt.bidirhandshake.movieapi.api.endpoints;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelpController {
    @RequestMapping(value = "/help")
    public String index() {
        System.out.println("swagger-ui.html");
        return "redirect:swagger-ui.html";
    }
}