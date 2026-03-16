package com.pao.laboratory03.exercise.model;

import com.pao.laboratory03.exercise.exception.InvalidGradeException;
import com.pao.laboratory03.exercise.exception.InvalidStudentException;

import java.util.HashMap;
import java.util.Map;

public class Student {
    private String name;
    private int age;
    private Map<Subject, Double> grades;

    public Student(String name, int age) {
        if(age < 18 || age > 60){
            throw new InvalidStudentException("Varsta " + age + " nu este valida");
        }
        this.name = name;
        this.age = age;
        this.grades = new HashMap<>();
    }

    public Map<Subject, Double> getGrades() {
        return grades;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void addGrade(Subject subject, double grade){
        if(grade < 1 || grade > 10){
            throw new InvalidGradeException("Nota " + grade + "nu este  valida(1-10)");
        }
        this.grades.put(subject, grade);
    }
    public double getAverage(){
        if(this.grades.isEmpty()){
            return 0;
        }
        double suma = 0;
        for(double g : this.grades.values()){
            suma += g;
        }
        double avg = suma /= grades.size();
        return avg;
    }

    public String toString() {
        return "Student " + "{name = " + getName() + " age = " + getAge() + " avg = " + getAverage() + "}";
    }
}
