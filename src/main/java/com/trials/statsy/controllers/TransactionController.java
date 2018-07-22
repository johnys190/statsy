package com.trials.statsy.controllers;

import com.trials.statsy.models.Transaction;
import com.trials.statsy.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @RequestMapping(method = POST, headers = "content-type=application/json", path ="/transactions")
    public ResponseEntity<String> transaction(@RequestBody Transaction transaction) {
        if (transactionService.add(transaction)) {
            return new ResponseEntity<>(HttpStatus.valueOf(201));
        }
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }
}
