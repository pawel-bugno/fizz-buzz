package com.pawelbugno;

public class NumberLabelsPrinter {

    public void printLabels(ArgumentParser argumentParser) {

        NumberLabelsGenerator generator = new NumberLabelsGenerator();
        generator.generateSequence(argumentParser.getStartNumber(), argumentParser.getEndNumber(),
                argumentParser.getDivisorsMap()).forEach(System.out::println);
    }
}
