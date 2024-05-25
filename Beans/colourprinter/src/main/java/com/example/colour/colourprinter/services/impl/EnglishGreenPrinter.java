package com.example.colour.colourprinter.services.impl;

import com.example.colour.colourprinter.services.GreenPrinter;
import org.springframework.stereotype.Component;

@Component
public class EnglishGreenPrinter implements GreenPrinter {
    @Override
    public String print() {
        return "green";
    }
}
