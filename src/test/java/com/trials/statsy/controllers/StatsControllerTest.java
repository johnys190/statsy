package com.trials.statsy.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trials.statsy.models.Statistics;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StatsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private StatsService statsService;

    @Test
    public void shouldReturnStatistics() throws Exception {
        Statistics stats = Statistics.builder().build();

        when(statsService.get()).thenReturn(stats);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .get(URI.create("/statistics"))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mvc.perform(builder).andExpect(status().is(200)).andReturn();

        assertEquals(stats, mapper.readValue(result.getResponse().getContentAsString(), Statistics.class));
    }
}