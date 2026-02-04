package com.victordemello.animalregistrationservice.controllers;

import com.victordemello.animalregistrationservice.dtos.RescueStatisticsResponse;
import com.victordemello.animalregistrationservice.services.RescueStatisticsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/statistics")
public class RescueStatisticsController {

    private final RescueStatisticsService rescueStatisticsService;

    public RescueStatisticsController(RescueStatisticsService rescueStatisticsService) {
        this.rescueStatisticsService = rescueStatisticsService;
    }

    @GetMapping("/rescues")
    public ResponseEntity<RescueStatisticsResponse> getRescueStatistics(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        RescueStatisticsResponse response = rescueStatisticsService.getRescueStatistics(startDate, endDate);
        return ResponseEntity.ok(response);
    }
}
