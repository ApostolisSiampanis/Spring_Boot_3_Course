package com.example.cruddemo;

import com.example.cruddemo.dao.StudentDAO;
import com.example.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {

        return runner -> {
            createStudent(studentDAO);
        };
    }

    private void createStudent(StudentDAO studentDAO) {

        // create a student object
        System.out.println("Creating new student object...");
        Student tempSTudent = new Student("Paul", "Doe", "paul@gmail.com");

        // save the student object
        System.out.println("Saving the student...");
        studentDAO.save(tempSTudent);

        // display id of the saved object
        System.out.println("Saved student. Generated id: " + tempSTudent.getId());
    }
}
