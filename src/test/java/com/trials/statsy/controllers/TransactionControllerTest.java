package com.trials.statsy.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trials.statsy.models.Transaction;
import com.trials.statsy.services.StatsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;
import java.time.Instant;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private StatsService statsService;

    @Test
    public void shouldReturn201InCaseOfSuccess() throws Exception {
        Transaction transaction = Transaction.builder().amount(2.0).timestamp(Instant.now()).build();

        when(statsService.add(transaction)).thenReturn(true);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .post(URI.create("/transactions"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(transaction));

        mvc.perform(builder).andExpect(status().is(201)).andReturn();
    }

    @Test
    public void shouldReturn204InCaseOfFailure() throws Exception {
        Transaction transaction = Transaction.builder().amount(2.0).timestamp(Instant.now()).build();

        when(statsService.add(transaction)).thenReturn(false);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .post(URI.create("/transactions"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(transaction));

        mvc.perform(builder).andExpect(status().is(204)).andReturn();
    }
}
