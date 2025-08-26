package com.kevin.loansim.service;

import com.kevin.loansim.dto.ApplicationRequest;
import com.kevin.loansim.dto.ApplicationResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class DecisionEngine {

    public ApplicationResponse decide(ApplicationRequest a) {
        List<String> reasons = new ArrayList<>();

        if (a.getCreditScore() < 520) {
            reasons.add("Credit score below 520 minimum.");
            return new ApplicationResponse("DENIED", 520, null, null, reasons);
        }

        double monthlyIncome = (a.getAnnualIncome() == 0) ? 0 : a.getAnnualIncome() / 12.0;
        double dti = (monthlyIncome == 0) ? 1.0 : a.getMonthlyDebt() / monthlyIncome;

        if (dti > 0.6) {
            reasons.add("Debt-to-income (DTI) above 0.60.");
            return new ApplicationResponse("DENIED", 600, null, null, reasons);
        }

        int score = 600;
        if (a.getCreditScore() >= 750) score += 220;
        else if (a.getCreditScore() >= 700) score += 160;
        else if (a.getCreditScore() >= 650) score += 100;
        else if (a.getCreditScore() >= 580) score += 50;

        if (dti <= 0.2) { score += 120; reasons.add("Very low DTI."); }
        else if (dti <= 0.35) { score += 60; reasons.add("Moderate DTI."); }
        else if (dti <= 0.5) { score += 10; reasons.add("High-but acceptable DTI."); }
        else { score -= 80; reasons.add("High DTI."); }

        switch (a.getEmploymentStatus().toUpperCase()) {
            case "EMPLOYED" -> { score += 40; reasons.add("Employed."); }
            case "SELF_EMPLOYED" -> { score += 20; reasons.add("Self-employed."); }
            case "STUDENT" -> reasons.add("Student.");
            case "UNEMPLOYED" -> { score -= 120; reasons.add("Unemployed."); }
            default -> reasons.add("Unknown employment.");
        }

        double lti = a.getLoanAmount() / Math.max(1.0, a.getAnnualIncome());
        if (lti <= 0.2) { score += 80; reasons.add("Loan small relative to income."); }
        else if (lti <= 0.35) { score += 30; reasons.add("Loan moderate relative to income."); }
        else { score -= 70; reasons.add("Loan large relative to income."); }

        score = Math.min(1000, Math.max(300, score));

        if (score < 680) {
            reasons.add("Score below 680 threshold.");
            return new ApplicationResponse("DENIED", score, null, null, reasons);
        }

        BigDecimal apr = mapScoreToApr(score);
        BigDecimal approvedAmount = BigDecimal.valueOf(a.getLoanAmount()).setScale(2, RoundingMode.HALF_UP);
        return new ApplicationResponse("APPROVED", score, apr, approvedAmount, reasons);
    }

    private BigDecimal mapScoreToApr(int score) {
        if (score >= 760) {
            double t = (1000 - Math.min(score, 1000)) / 240.0;
            double apr = 0.06 + (0.03 * t);
            return BigDecimal.valueOf(apr).setScale(4, RoundingMode.HALF_UP);
        } else {
            double t = (759 - Math.max(score, 680)) / 79.0;
            double apr = 0.10 + (0.07 * t);
            return BigDecimal.valueOf(apr).setScale(4, RoundingMode.HALF_UP);
        }
    }
}


