package pl.kl.weatherservice.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping(path = "/location")
    String printResponse() {
        return "Hello " + SecurityContextHolder.getContext().getAuthentication().getName() + "! You have access to endpoint: /location";
    }
}
