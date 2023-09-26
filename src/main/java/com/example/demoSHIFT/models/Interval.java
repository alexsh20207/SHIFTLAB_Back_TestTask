package com.example.demoSHIFT.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Interval<T> {
    private T start;
    private T end;

    public int getStartIndex() {
        String startStr = start.toString();
        int number = 0;
        try {
            number = (int)Double.parseDouble(startStr);
        }
        catch (NumberFormatException e) {
            return startStr.charAt(0);
        }
        return number;
    }
    public int getEndIndex() {
        String startStr = end.toString();
        int number = 0;
        try {
            number = (int)Double.parseDouble(startStr);
        }
        catch (NumberFormatException e) {
            return startStr.charAt(0);
        }
        return number;
    }
}
