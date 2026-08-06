package com.cgb.coffeegourmetb.controller;

import com.cgb.coffeegourmetb.dto.response.ReportResponse;
import com.cgb.coffeegourmetb.service.interfaces.ReportService;
import com.cgb.coffeegourmetb.util.constants.ApiPaths;
import com.cgb.coffeegourmetb.security.SecurityExpressions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping(ApiPaths.REPORTS)
@Tag(
        name = "Reportes",
        description = "API para consultas y reportes."
)
@SecurityRequirement(name = "bearerAuth")
public class ReportController {

    private final ReportService service;

    public ReportController(
            ReportService service) {

        this.service = service;
    }

    @Operation(summary = "Reporte de ventas")
    @GetMapping(ApiPaths.REPORTS_SALES)
    @PreAuthorize(SecurityExpressions.REPORTS_READ)
    public ReportResponse salesReport(

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fechaInicio,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fechaFin) {

        return service.salesReport(
                fechaInicio,
                fechaFin);
    }

}