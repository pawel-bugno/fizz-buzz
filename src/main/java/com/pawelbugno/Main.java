package com.pawelbugno;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        NumberLabelsGenerator generator = new NumberLabelsGenerator();
        generator.generateSequence(1, 100, Map.of(3, "Fizz", 5, "Buzz"))
                .forEach(System.out::println);
    }
}
