package com.trials.statsy.services;

import com.trials.statsy.models.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

import static org.junit.Assert.*;

public class TransactionServiceTest {

    private TransactionService transactionService;

    @Before
    public void setUp() throws Exception {
        transactionService = new TransactionService();
    }

    @Test
    public void add() {
        Transaction transaction = Transaction.builder().amount(2.0).timestamp(Instant.now()).build();

        assertTrue(transactionService.add(transaction));
    }
}