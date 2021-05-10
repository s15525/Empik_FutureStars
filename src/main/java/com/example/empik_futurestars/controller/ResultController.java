package com.example.empik_futurestars.controller;
import com.example.empik_futurestars.repository.ResultRepository;
import com.example.empik_futurestars.service.Operations;
import com.example.empik_futurestars.service.Result;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResultController {
    private static final String template = "%s";
    final Operations operations;
    AnnotationConfigApplicationContext context;
    ResultRepository repository;

    public ResultController(Operations operations) {
        context = new AnnotationConfigApplicationContext();
        context.scan("com.example.empik_futurestars.repository");
        context.refresh();
        repository = context.getBean(ResultRepository.class);
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
        context.close();
        return repository.retrieve(result.getResult());
    }

}
