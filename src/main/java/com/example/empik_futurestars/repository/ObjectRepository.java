package com.example.empik_futurestars.repository;

public interface ObjectRepository<T> {
    public void store(T t);
    public T retrieve(String id);
}
