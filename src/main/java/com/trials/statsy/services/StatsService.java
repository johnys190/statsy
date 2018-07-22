package com.trials.statsy.services;

import com.trials.statsy.models.Transaction;
import com.trials.statsy.repositories.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    @Autowired
    private StatsRepository statsRepository;

    public boolean add(Transaction transaction) {
        return statsRepository.add(transaction);
    }
}
