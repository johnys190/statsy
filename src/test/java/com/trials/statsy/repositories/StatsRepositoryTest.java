package com.trials.statsy.repositories;

import com.trials.statsy.models.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class StatsRepositoryTest {

    private StatsRepository statsRepository = new StatsRepository();

    @Test
    public void addShouldReturnFalseIfTransactionOld() {
        Transaction transaction = Transaction.builder().amount(2.0).timestamp(Instant.now().minusSeconds(120)).build();
        assertFalse(statsRepository.add(transaction));
    }

    @Test
    public void addShouldReturnTrueOnSuccess() {
        Transaction transaction = Transaction.builder().amount(2.0).timestamp(Instant.now()).build();
        assertTrue(statsRepository.add(transaction));
    }

    @Test
    public void addShouldAddTransactions() {
        Instant now = Instant.now();
        Transaction transaction1 = Transaction.builder().amount(2.0).timestamp(now).build();
        Transaction transaction2 = Transaction.builder().amount(3.0).timestamp(now.minusSeconds(59)).build();

        statsRepository.add(transaction1);
        statsRepository.add(transaction2);

        assertEquals(2, statsRepository.get().size());
    }

}