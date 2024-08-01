package com.example.demo.services;

import com.example.demo.dtos.StatisticsDTO;

public interface StatisticsService {

    public StatisticsDTO getMonthlyStatistics(Long id);
}
