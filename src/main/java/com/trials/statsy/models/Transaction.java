package com.trials.statsy.models;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class Transaction {
    Double amount;
    Instant timestamp;
}
