package org.example.codigoshttp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class CodigosHTTPController {

    @GetMapping("/")
    public String result(){
        return "result";
    }

    @GetMapping("/suma/{num1}/{num2}")
    public String suma(@PathVariable int num1, @PathVariable int num2, Model model) {
        model.addAttribute("operation", "Suma");
        model.addAttribute("result", num1 + num2);
        return "result";
    }

    @GetMapping("/division/{num1}/{num2}")
    public String division(@PathVariable int num1, @PathVariable int num2, Model model) {
        if (num2 == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede dividir por cero");
        }
        model.addAttribute("operation", "División");
        model.addAttribute("result", (double) num1 / num2);
        return "result";
    }

    @GetMapping("/raiz/{num}")
    public String raiz(@PathVariable int num, Model model) {
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Acceso no autorizado a la ruta /raiz");
    }

    @GetMapping("/factorial/{num}")
    public String factorial(@PathVariable int num, Model model) {
        if (num < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No existe factorial para números negativos");
        }
        if (num > 100) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No se permite calcular factorial de números mayores a 100");
        }
        model.addAttribute("operation", "Factorial");
        model.addAttribute("result", calcularFactorial(num));
        return "result";
    }

    private long calcularFactorial(int num) {
        long result = 1;
        for (int i = 2; i <= num; i++) {
            result *= i;
        }
        return result;
    }
}
