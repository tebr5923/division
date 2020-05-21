package com.foxminded.printer;

import java.util.List;

public class ConsolePrinter implements Printer{
    public void print(List<String> representation) {
        representation.forEach(System.out::println);
    }
}
