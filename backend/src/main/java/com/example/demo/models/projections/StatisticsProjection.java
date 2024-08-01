package com.example.demo.models.projections;

import java.math.BigDecimal;

public interface StatisticsProjection {

    Long getCount();

    BigDecimal getIncome();

    BigDecimal getOutcome();
}
