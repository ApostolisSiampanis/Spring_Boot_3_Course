package com.example.demo.rest;

public class StudentNotFoundExcepetion extends RuntimeException {

    public StudentNotFoundExcepetion(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentNotFoundExcepetion(String message) {
        super(message);
    }

    public StudentNotFoundExcepetion(Throwable cause) {
        super(cause);
    }
}
