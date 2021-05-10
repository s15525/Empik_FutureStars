package com.example.empik_futurestars.controller;
import com.example.empik_futurestars.repository.ResultRepository;
import com.example.empik_futurestars.service.Operations;
import com.example.empik_futurestars.service.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResultController {
    private static final String template = "%s";
    private final Operations operations;
    private final ResultRepository repository;

    public ResultController(Operations operations,ResultRepository repository) {
        this.repository = repository;
        this.operations = operations;
    }

    @GetMapping("/add")
    public Result greeting(@RequestParam(value = "numbers", defaultValue = "0") String numbers) {
        int sumResult = operations.Add(numbers);
        Result result;
        Result resultFromRep = repository.retrieve(String.valueOf(sumResult));
        try {
            result = new Result(resultFromRep.getCount()+1,String.format(template,resultFromRep.getResult()));
        }catch (NullPointerException a){
            result = new Result(0,String.format(template,sumResult));
        }
        repository.store(result);
        operations.setResult(0);
        return repository.retrieve(result.getResult());
    }

}
