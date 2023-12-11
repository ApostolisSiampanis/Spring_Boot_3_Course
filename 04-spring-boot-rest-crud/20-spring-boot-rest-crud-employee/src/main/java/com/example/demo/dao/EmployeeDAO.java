package com.example.demo.dao;

import com.example.demo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();
}
