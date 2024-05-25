package com.example.colour.colourprinter.services.impl;

import com.example.colour.colourprinter.services.BluePrinter;
import org.springframework.stereotype.Component;

@Component
public class EnglishBluePrinter implements BluePrinter {
    @Override
    public String print() {
        return "blue";
    }
}
