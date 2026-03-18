package com.pao.laboratory03.exercise.service;
import com.pao.laboratory03.exercise.exception.StudentNoutFoundException;
import com.pao.laboratory03.exercise.model.Student;
import com.pao.laboratory03.exercise.model.Subject;

import java.util.*;


public class StudentService {
    private static StudentService instanta = null;
    private List<Student> students;

    private StudentService(){
        this.students = new ArrayList<>();
    }

    public static StudentService getInstance(){
        if(instanta == null)
            instanta = new StudentService();
        return instanta;
    }

    public void addStudent(String name, int age){
        for(Student s : this.students){
            if(s.getName().equalsIgnoreCase(name)) {
                throw new RuntimeException("Studentul" + name + "exista deja");
            }
        }
        Student stud = new Student(name, age);
        this.students.add(stud);
    }

    public Student findByName(String name){
        for(Student s : this.students){
            if(s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        throw new StudentNoutFoundException("Studentul " + name + "nu a fost gasit");
    }
    public void addGrade(String studentName, Subject subject, double grade){
        Student s = findByName(studentName);
        s.addGrade(subject, grade);
    }

    public void printAllStudents(){
        if(this.students.isEmpty()){
            System.out.println("nu exista studenti");
            return;
        }
        int i = 1;
        for(Student s : this.students){
            System.out.println(i + ". " + s);
            if(s.getGrades().isEmpty())
            {
                System.out.println("nu are note.");
            }
            else
            {
                System.out.println("NOTE");
                for (Map.Entry<Subject, Double> element : s.getGrades().entrySet())
                {
                    System.out.println("   " + element.getKey().name() + " = " + element.getValue());
                }
            }
            i++;
        }
    }

    public void printTopStudents(){
        List<Student> lista = new ArrayList<>(this.students);
        lista.sort(Comparator.comparingDouble(Student::getAverage).reversed());
        int i = 1;
        for(Student s : lista){
            System.out.println("Locul " + i + " studentul/a " + s.getName() + " cu media " + s.getAverage());
            i++;
        }
    }

    public Map<Subject, Double> getAveragePerSubject(){
        Map<Subject, Double> dict = new HashMap<>();
        for(Subject s : Subject.values()){
            double suma = 0;
            int cnt = 0;
            for(Student snt : this.students){
                if(snt.getGrades().containsKey(s)){
                    suma += snt.getGrades().get(s);
                    cnt ++;
                }
            }
            if(cnt > 0){
                dict.put(s, suma / cnt);
            }
        }
        return dict;
    }

}
