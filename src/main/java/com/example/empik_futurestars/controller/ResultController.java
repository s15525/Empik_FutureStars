package com.example.empik_futurestars.controller;
import com.example.empik_futurestars.service.Operations;
import com.example.empik_futurestars.service.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResultController {
    private static final String template = "%s";
    final Operations operations;

    public ResultController(Operations operations) {
        this.operations = operations;
    }

    @GetMapping("/add")
    public Result greeting(@RequestParam(value = "numbers", defaultValue = "0") String numbers) {
        operations.setResult(0);
        return new Result(String.format(template,operations.Add(numbers)));
    }

}
