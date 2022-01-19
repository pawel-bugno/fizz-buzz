package com.pawelbugno;

public class Main {

    public static void main(String[] args) {
        ArgumentParser argumentParser = new ArgumentParser(args);
        NumberLabelsPrinter printer = new NumberLabelsPrinter();
        printer.printLabels(argumentParser);
    }
}
