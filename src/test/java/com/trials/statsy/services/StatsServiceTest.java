package com.trials.statsy.services;

import com.trials.statsy.models.Transaction;
import com.trials.statsy.repositories.StatsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatsServiceTest {

    @Mock
    private StatsRepository statsRepository;

    @InjectMocks
    private  StatsService statsService;

    @Test
    public void addShouldReturnTrueIfSuccessful() {
        Transaction transaction = Transaction.builder().amount(2.0).timestamp(Instant.now()).build();
        when(statsRepository.add(transaction)).thenReturn(true);
        assertTrue(statsService.add(transaction));
    }

    @Test
    public  void addShouldReturnFalseIfFail() {
        Transaction transaction = Transaction.builder().amount(2.0).timestamp(Instant.now()).build();
        when(statsRepository.add(transaction)).thenReturn(false);
        assertFalse(statsService.add(transaction));
    }
}