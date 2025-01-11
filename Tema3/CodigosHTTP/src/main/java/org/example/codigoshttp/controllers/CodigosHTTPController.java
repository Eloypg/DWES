package org.example.codigoshttp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

@Controller
public class CodigosHTTPController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/error/404")
    public String err404() {
        throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
    }
    @GetMapping("/error/401")
    public String err401() {
        throw new ResponseStatusException(UNAUTHORIZED, "Not authorized");
    }
    @GetMapping("/error/403")
    public String err403() {
        throw new ResponseStatusException(FORBIDDEN, "Permissions denied");
    }
    @GetMapping("500")
    public String err500() {
        throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Server error");
    }



}
