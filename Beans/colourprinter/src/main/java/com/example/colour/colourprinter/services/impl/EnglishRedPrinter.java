package com.example.colour.colourprinter.services.impl;

import com.example.colour.colourprinter.services.RedPrinter;
import org.springframework.stereotype.Component;

@Component
public class EnglishRedPrinter implements RedPrinter {
    @Override
    public String print() {
        return "red";
    }
}
