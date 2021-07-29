package ru.abenefic.gbfinal.task.hibernate;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentsRepository studentsRepository = new StudentsRepository();
        for (int i = 0; i < 1000; i++) {
            Student student = new Student();
            student.setMark(Math.random() * 5);
            student.setName("Student " + i);
            studentsRepository.save(student);
        }

        Student student = studentsRepository.findById(555L);
        student.setName(student.getName() + " modified");
        studentsRepository.save(student);

        for (long i = 1; i < 1000; i *= 4) {
            student = studentsRepository.findById(i);
            System.out.println(student);
            studentsRepository.remove(student);
        }
        student = studentsRepository.findById(555L);
        System.out.println(student);
        List<Student> students = studentsRepository.findAll();
        System.out.println(students.size());
    }
}
