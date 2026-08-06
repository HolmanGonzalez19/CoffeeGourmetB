package com.cgb.coffeegourmetb.service.interfaces;

import com.cgb.coffeegourmetb.dto.response.ReportResponse;

import java.time.LocalDate;

public interface ReportService {

    ReportResponse salesReport(
            LocalDate fechaInicio,
            LocalDate fechaFin
    );

}