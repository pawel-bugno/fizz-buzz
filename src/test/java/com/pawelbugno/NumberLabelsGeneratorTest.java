package com.pawelbugno;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberLabelsGeneratorTest {

    private NumberLabelsGenerator generator;

    @BeforeEach
    public void setUp() {
        generator = new NumberLabelsGenerator();
    }

    @Test
    public void shouldGenerateNumberWhenItsNotDivisibleByAny() {
        // when
        List<String> result = getLabelsForNumber(1);

        // then
        List<String> expectedResult = List.of("1");
        assertEquals(result, expectedResult);
    }

    @Test
    public void shouldGenerateNumberWhenItsDivisibleByOneOfDivisors() {
        // when
        List<String> result = getLabelsForNumber(3);

        // then
        List<String> expectedResult = List.of("Fizz");
        assertEquals(result, expectedResult);
    }

    @Test
    public void shouldGenerateNumberWhenItsDivisibleByMoreThanOneOfDivisors() {
        // when
        List<String> result = getLabelsForNumber(15);

        // then
        List<String> expectedResult = List.of("Fizz", "Buzz");
        assertThat(result).containsExactlyInAnyOrderElementsOf(expectedResult);
    }

    @Test
    public void shouldGenerateNumbersWhenNoDivisorsGiven() {
        // when
        List<String> result = generator.generateSequence(1, 5, Map.of())
                .collect(Collectors.toUnmodifiableList());

        // then
        List<String> expectedResult = List.of("1", "2", "3", "4", "5");
        assertEquals(result, expectedResult);
    }

    @Test
    public void shouldGenerateNumbersAndLabelsForTwoDivisorsGiven() {
        // when
        List<String> result = generator.generateSequence(1, 5, Map.of(2, "Fizz", 3, "Buzz"))
                .collect(Collectors.toUnmodifiableList());

        // then
        List<String> expectedResult = List.of("1", "Fizz", "Buzz", "Fizz", "5");
        assertEquals(result, expectedResult);
    }

    @Test
    public void shouldGenerateProperNumberOfLabelsForTwoDivisorsGiven() {
        // when
        List<String> result = generator.generateSequence(1, 6, Map.of(2, "Fizz", 3, "Buzz"))
                .collect(Collectors.toUnmodifiableList());

        // then
        assertThat(result).hasSize(7);
    }

    @Test
    public void shouldThrowExceptionWhenDivisorIsEqualToZero() {
        // when
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> generator.generateSequence(1, 2, Map.of(0, "Fizz"))
                        .collect(Collectors.toList()));

        // then
        String expectedMessage = "Divisor must be different than zero";
        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    public void shouldThrowExceptionWhenStartNumberIsGreaterThanEndNumber() {
        // when
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> generator.generateSequence(2, 1, Map.of(2, "Fizz"))
                        .collect(Collectors.toList()));

        // then
        String expectedMessage = "Start number must be less or equal than end number";
        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    public void shouldThrowExceptionWhenDivisorsMapIsNull() {
        // when
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> generator.generateSequence(1, 2, null).collect(Collectors.toList()));

        // then
        String expectedMessage = "Divisors to labels map cannot be null";
        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    }

    private List<String> getLabelsForNumber(int number) {
        return generator.generateSequence(number, number, Map.of(3, "Fizz", 5, "Buzz"))
                .collect(Collectors.toUnmodifiableList());
    }
}
