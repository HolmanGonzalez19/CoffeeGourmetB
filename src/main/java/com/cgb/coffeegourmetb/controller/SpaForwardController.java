//habilitar para produccion
package com.cgb.coffeegourmetb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpaForwardController {

    @GetMapping({
            "/dashboard",
            "/cash-register",
            "/pos",
            "/productos",
            "/inventario",
            "/compras",
            "/ventas",
            "/estadisticas"
    })
    public String forwardToAngular() {
        return "forward:/index.html";
    }
}