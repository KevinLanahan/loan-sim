package com.kevin.loansim.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevin.loansim.domain.LoanApplication;
import com.kevin.loansim.dto.ApplicationRequest;
import com.kevin.loansim.dto.ApplicationResponse;
import com.kevin.loansim.repository.LoanApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    private final DecisionEngine decisionEngine;
    private final LoanApplicationRepository repo;
    private final ObjectMapper mapper;

    public ApplicationService(DecisionEngine decisionEngine,
                              LoanApplicationRepository repo,
                              ObjectMapper mapper) {
        this.decisionEngine = decisionEngine;
        this.repo = repo;
        this.mapper = mapper;
    }

    public ApplicationResponse submit(ApplicationRequest req) {
        // 1) get decision from engine
        ApplicationResponse resp = decisionEngine.decide(req);

        // 2) persist combined request + decision
        LoanApplication e = new LoanApplication();
        e.setFirstName(req.getFirstName());
        e.setLastName(req.getLastName());
        e.setEmail(req.getEmail());
        e.setEmploymentStatus(req.getEmploymentStatus());
        e.setAnnualIncome(req.getAnnualIncome());
        e.setMonthlyDebt(req.getMonthlyDebt());
        e.setCreditScore(req.getCreditScore());
        e.setLoanAmount(req.getLoanAmount());
        e.setLoanTermMonths(req.getLoanTermMonths());

        e.setStatus(resp.getStatus());
        e.setRiskScore(resp.getRiskScore());
        e.setApr(resp.getApr());
        e.setApprovedAmount(resp.getApprovedAmount());
        try {
            e.setReasonsJson(mapper.writeValueAsString(resp.getReasons()));
        } catch (JsonProcessingException ex) {
            e.setReasonsJson("[]");
        }

        repo.save(e);
        return resp;
    }

    public List<LoanApplication> historyByEmail(String email) {
        return repo.findByEmailOrderByCreatedAtDesc(email);
    }
}

