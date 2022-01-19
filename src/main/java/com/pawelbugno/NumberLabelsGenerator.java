package com.pawelbugno;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

public class NumberLabelsGenerator {

    private int currentNumber;

    public Stream<String> generateSequence(int startNumber, int endNumber, Map<Integer, String> divisorsToLabelsMap) {

        checkSequenceParameters(startNumber, endNumber, divisorsToLabelsMap);
        this.currentNumber = startNumber;

        return Stream.generate(() -> generateLabelsForCurrentNumber(divisorsToLabelsMap))
                .limit(endNumber - startNumber + 1)
                .flatMap(List::stream);
    }

    private void checkSequenceParameters(int startNumber, int endNumber, Map<Integer, String> divisorsToLabelsMap) {

        if (startNumber > endNumber) {
            throw new IllegalArgumentException("Start number must be less or equal than end number");
        }

        if (isNull(divisorsToLabelsMap)) {
            throw new IllegalArgumentException("Divisors to labels map cannot be null");
        }
    }

    private List<String> generateLabelsForCurrentNumber(Map<Integer, String> divisorsToLabelsMap) {

        List<String> labels = divisorsToLabelsMap.keySet().stream()
                .filter(this::isCurrentNumberDivisibleBy)
                .map(divisorsToLabelsMap::get)
                .collect(Collectors.toList());

        if (labels.isEmpty()) {
            labels.add(String.valueOf(currentNumber));
        }
        currentNumber++;

        return labels;
    }

    private boolean isCurrentNumberDivisibleBy(int divisor) {

        if (divisor == 0) {
            throw new IllegalArgumentException("Divisor must be different than zero");
        }

        return this.currentNumber % divisor == 0;
    }
}
