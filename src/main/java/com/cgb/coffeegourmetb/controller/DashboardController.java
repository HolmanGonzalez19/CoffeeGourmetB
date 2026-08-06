package com.cgb.coffeegourmetb.controller;

import com.cgb.coffeegourmetb.dto.response.DashboardResponse;
import com.cgb.coffeegourmetb.service.interfaces.DashboardService;
import com.cgb.coffeegourmetb.util.constants.ApiPaths;
import com.cgb.coffeegourmetb.security.SecurityExpressions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPaths.DASHBOARD)
@Tag(
        name = "Dashboard",
        description = "Indicadores principales del sistema."
)
@SecurityRequirement(name = "bearerAuth")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(
            DashboardService service) {

        this.service = service;
    }

    @Operation(summary = "Consultar dashboard")
    @GetMapping
    @PreAuthorize(SecurityExpressions.DASHBOARD_READ)
    public DashboardResponse getDashboard() {

        return service.getDashboard();
    }

}