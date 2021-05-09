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

    public int Add(String numbers) {
        Pattern pattern = Pattern.compile(",\\\\");

        if (pattern.matcher(numbers).find()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad char before new line expresion!");
        } else {
            String[] splitNumbers = numbers.split("\\\\n|,");

            for (String number :
                    splitNumbers) {
                try {
                    result += Integer.parseInt(number);
                }catch(NumberFormatException ignored){

                }
            }
            return result;
        }
    }

    public void setResult(int result) {
        this.result = result;
    }
}
