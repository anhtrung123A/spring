package com.example.AES.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileFormat {
    private String text;
    private String color;
    private boolean isBold;
    private boolean isItalic;
    private boolean isUnderline;
}
