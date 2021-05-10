package com.example.empik_futurestars.service;

public class Result {
    private int count;
    private String result;

    public Result(int count, String result) {
        this.count = count;
        this.result = result;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
