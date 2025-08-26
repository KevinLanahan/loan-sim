package com.kevin.loansim.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan_applications")
public class LoanApplication {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    private String firstName;
    private String lastName;
    private String email;
    private String employmentStatus;

    private Double annualIncome;
    private Double monthlyDebt;
    private Integer creditScore;
    private Double loanAmount;
    private Integer loanTermMonths;

    private String status;               
    private Integer riskScore;
    private BigDecimal apr;
    private BigDecimal approvedAmount;

    @Column(columnDefinition = "TEXT")
    private String reasonsJson;

    @PrePersist
    void onCreate() { this.createdAt = LocalDateTime.now(); }

    public Long getId() { return id; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String v) { this.firstName = v; }
    public String getLastName() { return lastName; }
    public void setLastName(String v) { this.lastName = v; }
    public String getEmail() { return email; }
    public void setEmail(String v) { this.email = v; }
    public String getEmploymentStatus() { return employmentStatus; }
    public void setEmploymentStatus(String v) { this.employmentStatus = v; }
    public Double getAnnualIncome() { return annualIncome; }
    public void setAnnualIncome(Double v) { this.annualIncome = v; }
    public Double getMonthlyDebt() { return monthlyDebt; }
    public void setMonthlyDebt(Double v) { this.monthlyDebt = v; }
    public Integer getCreditScore() { return creditScore; }
    public void setCreditScore(Integer v) { this.creditScore = v; }
    public Double getLoanAmount() { return loanAmount; }
    public void setLoanAmount(Double v) { this.loanAmount = v; }
    public Integer getLoanTermMonths() { return loanTermMonths; }
    public void setLoanTermMonths(Integer v) { this.loanTermMonths = v; }
    public String getStatus() { return status; }
    public void setStatus(String v) { this.status = v; }
    public Integer getRiskScore() { return riskScore; }
    public void setRiskScore(Integer v) { this.riskScore = v; }
    public BigDecimal getApr() { return apr; }
    public void setApr(BigDecimal v) { this.apr = v; }
    public BigDecimal getApprovedAmount() { return approvedAmount; }
    public void setApprovedAmount(BigDecimal v) { this.approvedAmount = v; }
    public String getReasonsJson() { return reasonsJson; }
    public void setReasonsJson(String v) { this.reasonsJson = v; }
}
