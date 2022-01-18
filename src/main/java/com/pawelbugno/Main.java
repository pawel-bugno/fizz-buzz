package com.pawelbugno;

public class Main {

    public static void main(String[] args) {
        NumberLabelsGenerator generator = new NumberLabelsGenerator();
        generator.generateSequence(1, 100).forEach(System.out::println);
    }
}
