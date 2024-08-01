package com.example.demo.services;

import com.example.demo.dtos.StatisticsDTO;
import com.example.demo.repos.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public StatisticsDTO getMonthlyStatistics(Long id) {
        var repOutcome = transactionRepository.findStats(id);
        return new StatisticsDTO(
                repOutcome.getIncome(),
                repOutcome.getOutcome(),
                repOutcome.getCount()
        );
    }
}
