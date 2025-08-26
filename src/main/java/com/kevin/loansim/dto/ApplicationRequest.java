package com.kevin.loansim.dto;

import jakarta.validation.constraints.*;

public class ApplicationRequest {
    @NotBlank private String firstName;
    @NotBlank private String lastName;
    @Email @NotBlank private String email;

    @NotNull @PositiveOrZero private Double annualIncome;
    @NotNull @PositiveOrZero private Double monthlyDebt;
    @NotNull @Min(300) @Max(850) private Integer creditScore;

    @NotNull @Positive private Double loanAmount;
    @NotNull @Min(6) @Max(84) private Integer loanTermMonths;

    @NotBlank private String employmentStatus;

    public String getFirstName()
        { return firstName; }

    public void setFirstName(String v)
        { this.firstName = v; }

    public String getLastName()
        { return lastName; }

    public void setLastName(String v)
        { this.lastName = v; }

    public String getEmail()
        { return email; }

    public void setEmail(String v)
        { this.email = v; }

    public Double getAnnualIncome()
        { return annualIncome; }

    public void setAnnualIncome(Double v)
        { this.annualIncome = v; }

    public Double getMonthlyDebt()
        { return monthlyDebt; }

    public void setMonthlyDebt(Double v)
        { this.monthlyDebt = v; }

    public Integer getCreditScore()
        { return creditScore; }

    public void setCreditScore(Integer v)
        { this.creditScore = v; }

    public Double getLoanAmount()
        { return loanAmount; }

    public void setLoanAmount(Double v)
        { this.loanAmount = v; }

    public Integer getLoanTermMonths()
        { return loanTermMonths; }

    public void setLoanTermMonths(Integer v)
        { this.loanTermMonths = v; }

    public String getEmploymentStatus()
        { return employmentStatus; }

    public void setEmploymentStatus(String v)
        { this.employmentStatus = v; }
}
