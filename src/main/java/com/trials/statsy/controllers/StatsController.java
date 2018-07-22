package com.trials.statsy.controllers;

import com.trials.statsy.models.Statistics;
import com.trials.statsy.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class StatsController {
    @Autowired
    StatsService statsService;

    @RequestMapping(method = GET, headers = "content-type=application/json", path ="/statistics")
    public ResponseEntity<Statistics> statistics() {
            return new ResponseEntity<> (statsService.get(), HttpStatus.OK);
    }
}
