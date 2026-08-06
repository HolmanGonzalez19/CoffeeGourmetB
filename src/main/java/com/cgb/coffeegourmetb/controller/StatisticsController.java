package com.cgb.coffeegourmetb.controller;

import com.cgb.coffeegourmetb.dto.response.StatisticsResponse;
import com.cgb.coffeegourmetb.service.interfaces.StatisticsService;
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
@RequestMapping(ApiPaths.STATISTICS)
@Tag(
        name = "Estadísticas",
        description = "API para consultar estadísticas del sistema."
)
@SecurityRequirement(name = "bearerAuth")
public class StatisticsController {

    private final StatisticsService service;

    public StatisticsController(
            StatisticsService service) {

        this.service = service;
    }

    @Operation(summary = "Obtener estadísticas generales")
    @GetMapping
    @PreAuthorize(SecurityExpressions.STATISTICS_READ)
    public StatisticsResponse getStatistics() {

        return service.getStatistics();
    }

}