package com.trials.statsy.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Statistics {
    private double sum;
    private double avg;
    private double max;
    private double min;
    private long count;
}
