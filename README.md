# FizzBuzz - number labels listing application

## General info
The main goal of this application is writing numbers from the start one to the end one. Both of them are declared by the user. Numbers can be replaced by the labels when user inputs key-value pairs where the key is potential divisor of printed number and the value is the label to be displayed instead of number if the key is divisor of the current number. If current number is divisible by more than one divisor then all of them are printed instead of the number.

## Requirements 
This application requires installed Java 11 or higher and Maven 3.6.3 or higher.

## Usage
Application should be run from the same location as this documentation is stored.
At first it must be built. You can do it by running `mvn compile`. As soon as the building process is finished you can run the application by invoking the following command: 

`mvn exec:java -Dexec.mainClass=com.pawelbugno.Main -Dexec.args="<start_number> <end_number> <divisor1> <label1> <divisor2> <label2> ..."`

So the command `mvn exec:java -Dexec.mainClass=com.pawelbugno.Main -Dexec.args="1 20 3 Fizz 5 Buzz"`" should give result like:
```
1
2
Fizz
4
Buzz
Fizz
7
8
Fizz
Buzz
11
Fizz
13
14
Fizz
Buzz
16
17
Fizz
19
Buzz
```

List of divisors-labels entries is optional but it cannot lack of key or value.