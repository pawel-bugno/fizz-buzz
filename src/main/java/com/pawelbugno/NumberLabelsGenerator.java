package com.pawelbugno;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class NumberLabelsGenerator {

    private int currentNumber;

    public Stream<String> generateSequence(int startNumber, int endNumber, Map<Integer, String> divisorsToLabelsMap) {

        if (startNumber < 0 || startNumber > endNumber) {
            throw new IllegalArgumentException("Start number must be positive and less or equal than end number");
        }

        if (Objects.isNull(divisorsToLabelsMap)) {
            throw new IllegalArgumentException("Divisors to labels map cannot be null");
        }
        this.currentNumber = startNumber;

        return Stream.generate(() -> generateNextLabels(divisorsToLabelsMap))
                .limit(endNumber - startNumber + 1)
                .flatMap(List::stream);
    }

    private List<String> generateNextLabels(Map<Integer, String> divisorsToLabelsMap) {

        List<String> labels = new ArrayList<>();
        for (Integer divisor : divisorsToLabelsMap.keySet()) {
            if (isCurrentNumberDivisibleBy(divisor)) {
                labels.add(divisorsToLabelsMap.get(divisor));
            }
        }

        if (labels.isEmpty()) {
            labels.add(String.valueOf(currentNumber));
        }
        currentNumber++;

        return labels;
    }

    private boolean isCurrentNumberDivisibleBy(int divisor) {

        if (divisor <= 0) {
            throw new IllegalArgumentException("Divisor must be greater than zero");
        }

        return this.currentNumber % divisor == 0;
    }
}
