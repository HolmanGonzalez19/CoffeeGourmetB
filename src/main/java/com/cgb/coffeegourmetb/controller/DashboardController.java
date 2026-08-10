package com.cgb.coffeegourmetb.controller;

import com.cgb.coffeegourmetb.dto.response.DashboardResponse;
import com.cgb.coffeegourmetb.service.interfaces.DashboardService;
import com.cgb.coffeegourmetb.util.constants.ApiPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPaths.DASHBOARD)
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(
            DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<DashboardResponse> obtenerDashboard() {

        return ResponseEntity.ok(
                dashboardService.obtenerDashboard()
        );
    }

}