package com.example.empik_futurestars.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class Operations {
    private int result;

    public Operations() {
    }

    private String getDelimiter(String numbers) {
        StringBuilder delimiters = new StringBuilder();
        Pattern delimiterPattern = Pattern.compile("(\\[.*?\\])");
        Matcher matcher = delimiterPattern.matcher(numbers);
        while (matcher.find()) {
            delimiters.append("|").append(matcher.group(1));
        }
        if (delimiters.length() == 0) {
            return delimiters.append("|").append(numbers.charAt(2)).toString();
        }
        return delimiters.toString();
    }


    public int Add(String numbers) {
        Pattern badExpresionPattern = Pattern.compile(",\\\\");
        Pattern optionalDelimiterPattern = Pattern.compile("//.*\\\\n");
        String[] splitNumbers;
        StringBuilder neativeNumbers = new StringBuilder();

        if (badExpresionPattern.matcher(numbers).find()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad char before new line expresion!");
        } else {
            if (optionalDelimiterPattern.matcher(numbers).find()) {
                splitNumbers = numbers.split("\\\\n" + getDelimiter(numbers));
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
                    if (result + temp > 1000) {
                        return result;
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
