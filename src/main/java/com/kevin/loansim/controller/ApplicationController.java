package com.kevin.loansim.controller;

import com.kevin.loansim.domain.LoanApplication;
import com.kevin.loansim.dto.ApplicationRequest;
import com.kevin.loansim.dto.ApplicationResponse;
import com.kevin.loansim.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    private final ApplicationService service;

    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    // --- Strongly-typed DTO for history responses ---
    public static record HistoryItem(
            Long id,
            String createdAt,
            String status,
            Integer riskScore,
            BigDecimal apr,
            BigDecimal approvedAmount,
            Double loanAmount,
            Integer loanTermMonths,
            String employmentStatus,
            String reasonsJson
    ) {}

    @PostMapping("/applications")
    public ResponseEntity<ApplicationResponse> submit(@Valid @RequestBody ApplicationRequest request) {
        return ResponseEntity.ok(service.submit(request));
    }

    @GetMapping("/applications")
    public List<HistoryItem> byEmail(@RequestParam String email) {
        return service.historyByEmail(email).stream()
                .map(e -> toHistoryItem(e))
                .toList();
    }

    private static HistoryItem toHistoryItem(LoanApplication e) {
        return new HistoryItem(
                e.getId(),
                e.getCreatedAt().toString(),
                e.getStatus(),
                e.getRiskScore(),
                e.getApr(),
                e.getApprovedAmount(),
                e.getLoanAmount(),
                e.getLoanTermMonths(),
                e.getEmploymentStatus(),
                e.getReasonsJson()
        );
    }
}
