package com.pawelbugno;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class NumberLabelsGenerator {

    private int currentNumber;

    public Stream<String> generateSequence(int startNumber, int endNumber) {
        if (startNumber < 0 || startNumber >= endNumber) {
            throw new IllegalArgumentException("Start number must be positive and greater than end number");
        }
        this.currentNumber = startNumber;
        return Stream.generate(this::generateNextLabels).limit(endNumber - startNumber + 1).flatMap(List::stream);
    }

    private List<String> generateNextLabels() {
        List<String> labels = new ArrayList<>();
        if (isCurrentNumberDivisibleBy(3)) {
            labels.add("Fizz");
        }
        if (isCurrentNumberDivisibleBy(5)) {
            labels.add("Buzz");
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
