package com.design.parkinglot.strategies.feecalculationstrategy;

import java.util.Date;

public interface FeeCalculationStrategy {
    double calculateFees(Date startDate, Date endDate);
}
