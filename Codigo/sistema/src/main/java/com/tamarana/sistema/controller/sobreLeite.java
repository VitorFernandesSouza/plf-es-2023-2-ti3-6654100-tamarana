package com.tamarana.sistema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class sobreLeite {
    @GetMapping("/sobreLeite")

    public String sobreleite() {

        return "/sobreLeite";

    }
}
