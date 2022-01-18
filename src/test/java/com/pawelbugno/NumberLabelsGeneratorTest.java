package com.pawelbugno;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberLabelsGeneratorTest {

    @Test
    public void shouldReplaceNumbersWithDivisors() {
        // given
        NumberLabelsGenerator generator = new NumberLabelsGenerator();
        List<String> expectedResult = List.of("1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8",
                "Fizz", "Buzz", "11", "Fizz", "13", "14", "Fizz", "Buzz", "16");

        // when
        List<String> result = generator.generateSequence(1, 16).collect(Collectors.toUnmodifiableList());

        // then
        assertEquals(result, expectedResult);
    }
}
