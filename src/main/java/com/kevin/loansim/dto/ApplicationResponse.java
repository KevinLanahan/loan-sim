package com.kevin.loansim.dto;

import java.math.BigDecimal;
import java.util.List;

public class ApplicationResponse {
    private String status;              
    private Integer riskScore;         
    private BigDecimal apr;            
    private BigDecimal approvedAmount; 
    private List<String> reasons;

    public ApplicationResponse() {}

    public ApplicationResponse(String status, Integer riskScore, BigDecimal apr, BigDecimal approvedAmount, List<String> reasons) {
        this.status = status;
        this.riskScore = riskScore;
        this.apr = apr;
        this.approvedAmount = approvedAmount;
        this.reasons = reasons;
    }

    public String getStatus() { return status; }
    public void setStatus(String v) { this.status = v; }
    public Integer getRiskScore() { return riskScore; }
    public void setRiskScore(Integer v) { this.riskScore = v; }
    public BigDecimal getApr() { return apr; }
    public void setApr(BigDecimal v) { this.apr = v; }
    public BigDecimal getApprovedAmount() { return approvedAmount; }
    public void setApprovedAmount(BigDecimal v) { this.approvedAmount = v; }
    public List<String> getReasons() { return reasons; }
    public void setReasons(List<String> v) { this.reasons = v; }
}
