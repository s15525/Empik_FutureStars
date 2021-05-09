package com.example.empik_futurestars.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class Operations {
    private int result;

    public Operations() {
    }

    public int Add(String numbers){

        String[] splitNumbers = numbers.split(",");

        for (String number:
             splitNumbers) {
            result += Integer.parseInt(number);
        }

        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
