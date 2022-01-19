package com.pawelbugno;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArgumentParserTest {

    @Test
    public void shouldParseTwoNumbers() {
        // given
        String[] input = {"1", "2"};

        // when
        ArgumentParser argumentParser = new ArgumentParser(input);

        // then
        assertEquals(argumentParser.getStartNumber(), 1);
        assertEquals(argumentParser.getEndNumber(), 2);
    }

    @Test
    public void shouldParseTwoNumbersWithOneDivisor() {
        // given
        String[] input = {"1", "2", "3", "Fizz"};

        // when
        ArgumentParser argumentParser = new ArgumentParser(input);

        // then
        assertEquals(argumentParser.getStartNumber(), 1);
        assertEquals(argumentParser.getEndNumber(), 2);
        assertEquals(argumentParser.getDivisorsMap(), Map.of(3, "Fizz"));
    }

    @Test
    public void shouldParseTwoNumbersWithMultipleDivisors() {
        // given
        String[] input = {"1", "2", "3", "Fizz", "5", "Buzz"};

        // when
        ArgumentParser argumentParser = new ArgumentParser(input);

        // then
        assertEquals(argumentParser.getStartNumber(), 1);
        assertEquals(argumentParser.getEndNumber(), 2);
        assertThat(argumentParser.getDivisorsMap()).containsExactlyInAnyOrderEntriesOf(Map.of(3, "Fizz", 5, "Buzz"));
    }

    @Test
    public void shouldThrowExceptionWhenZeroParametersGiven() {
        shouldThrowExceptionForLessThanTwoParameters(new String[]{});
    }

    @Test
    public void shouldThrowExceptionWhenOneParameterGiven() {
        shouldThrowExceptionForLessThanTwoParameters(new String[]{"1"});
    }

    @Test
    public void shouldThrowExceptionWhenOddNumberOfParametersGiven() {
        shouldThrowException(new String[]{"1", "2", "3"}, "There must be even number of parameters (key-value pairs)");
    }

    @Test
    public void shouldThrowExceptionWhenTextInsteadOfStartNumberGiven() {
        shouldThrowException(new String[]{"1a", "2", "3", "Buzz"}, "Start number must be an integer");
    }

    @Test
    public void shouldThrowExceptionWhenTextInsteadOfEndNumberGiven() {
        shouldThrowException(new String[]{"1", "2a", "3", "Buzz"}, "End number must be an integer");
    }

    @Test
    public void shouldThrowExceptionWhenTextInsteadOfDivisorGiven() {
        shouldThrowException(new String[]{"1", "2", "3a", "Buzz"}, "Divisor must be an integer");
    }

    private void shouldThrowExceptionForLessThanTwoParameters(String[] input) {
        shouldThrowException(input, "Give at least start and end numbers");
    }

    private void shouldThrowException(String[] input, String expectedMessage) {
        // when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new ArgumentParser(input));

        // then
        assertEquals(exception.getMessage(), expectedMessage);
    }
}
