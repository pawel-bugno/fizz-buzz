package com.pawelbugno;

import java.util.HashMap;
import java.util.Map;

public class ArgumentParser {

    private int startNumber;
    private int endNumber;
    private Map<Integer, String> divisorsMap;

    public ArgumentParser(String[] args) {
        checkArgumentsSize(args);
        this.startNumber = parseInt(args[0], "Start number");
        this.endNumber = parseInt(args[1], "End number");
        this.divisorsMap = parseMap(args);
    }

    public int getStartNumber() {
        return startNumber;
    }

    public int getEndNumber() {
        return endNumber;
    }

    public Map<Integer, String> getDivisorsMap() {
        return divisorsMap;
    }

    private static int parseInt(String number, String objectName) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(objectName + " must be an integer");
        }
    }

    private void checkArgumentsSize(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Give at least start and end numbers");
        }
        if (args.length % 2 == 1) {
            throw new IllegalArgumentException("There must be even number of parameters (key-value pairs)");
        }
    }

    private Map<Integer, String> parseMap(String[] args) {

        Map<Integer, String> map = new HashMap<>();
        for (int i = 2; i < args.length - 1; i += 2) {
            int divisor = parseInt(args[i], "Divisor");
            String label = args[i + 1];
            map.put(divisor, label);
        }

        return map;
    }
}
