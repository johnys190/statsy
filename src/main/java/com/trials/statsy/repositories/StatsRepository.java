package com.trials.statsy.repositories;

import com.trials.statsy.models.Transaction;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.Instant.now;

@Component
public class StatsRepository {

    private ConcurrentMap<Long, List<Transaction>> tranasctionMap = new ConcurrentHashMap<>();
    private Long timeWindow = 60L;

    public boolean add(Transaction transaction) {
        Long key = transaction.getTimestamp().getEpochSecond();
        if (isValid(key)) {
            tranasctionMap.merge(key, Collections.singletonList(transaction), (list1, list2) ->
                    Stream.of(list1, list2).flatMap(Collection::stream).collect(Collectors.toList()));
            return true;
        }
        return false;
    }

    public List<Transaction> get(Long key) {
        return tranasctionMap.getOrDefault(key, new ArrayList<>());
    }

    private boolean isValid(Long second) {
        return now().minusSeconds(timeWindow).getEpochSecond() <= second;
    }
}
