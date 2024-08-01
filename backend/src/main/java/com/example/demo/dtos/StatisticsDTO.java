package com.example.demo.dtos;

import java.math.BigDecimal;


public class StatisticsDTO {

    public StatisticsDTO(BigDecimal income, BigDecimal outcome, Long count) {
        this.income = income;
        this.outcome = outcome;
        this.count = count;
    }

    public StatisticsDTO() {
    }

    private BigDecimal income;

    private BigDecimal outcome;

    private Long count;

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getOutcome() {
        return outcome;
    }

    public void setOutcome(BigDecimal outcome) {
        this.outcome = outcome;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
