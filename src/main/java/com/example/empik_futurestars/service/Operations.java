package com.example.empik_futurestars.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Pattern;

@Service
public class Operations {
    private int result;

    public Operations() {
    }

    private char getDelimiter(String numbers) {
        return numbers.charAt(2);
    }


    public int Add(String numbers) {
        Pattern badNumbersPattern = Pattern.compile(",\\\\");
        Pattern optionalDelimiterPattern = Pattern.compile("//.\\\\n");
        String[] splitNumbers;
        StringBuilder neativeNumbers = new StringBuilder();

        if (badNumbersPattern.matcher(numbers).find()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad char before new line expresion!");
        } else {

            if (optionalDelimiterPattern.matcher(numbers).find()) {
                char delimiter = getDelimiter(numbers);
                splitNumbers = numbers.split("\\\\n|" + delimiter);
            } else {
                splitNumbers = numbers.split("\\\\n|,");
            }
            for (String number :
                    splitNumbers) {
                try {
                    int temp = Integer.parseInt(number);
                    if (temp < 0) {
                        neativeNumbers.append(" ").append(temp);
                    }
                    result += temp;
                } catch (NumberFormatException ignore) {
                }
            }

            if (neativeNumbers.length() > 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Negatives not allowed" + neativeNumbers);
            }

            return result;
        }
    }

    public void setResult(int result) {
        this.result = result;
    }
}
