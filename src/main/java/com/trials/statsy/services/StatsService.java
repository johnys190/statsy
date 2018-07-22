package com.trials.statsy.services;

import com.trials.statsy.models.Statistics;
import com.trials.statsy.models.Transaction;
import com.trials.statsy.repositories.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;

@Service
public class StatsService {

    @Autowired
    private StatsRepository statsRepository;

    public boolean add(Transaction transaction) {
        return statsRepository.add(transaction);
    }

    public Statistics get() {
        DoubleSummaryStatistics second = statsRepository.get()
                .stream()
                .collect(Collectors.summarizingDouble(Transaction::getAmount));
        return Statistics
                .builder()
                .avg(second.getAverage())
                .max(second.getMax())
                .min(second.getMin())
                .sum(second.getSum())
                .count(second.getCount())
                .build();
    }
}
