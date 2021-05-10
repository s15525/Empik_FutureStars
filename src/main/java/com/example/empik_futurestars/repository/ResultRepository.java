package com.example.empik_futurestars.repository;

import com.example.empik_futurestars.service.Result;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ResultRepository implements ObjectRepository<Result>{
    Map<String,Result> repository;

    public ResultRepository() {
        this.repository = new HashMap<>();
    }

    @Override
    public void store(Result result) {
        repository.put(result.getResult(),result);
    }

    @Override
    public Result retrieve(String id) {
        return repository.get(id);
    }
}
