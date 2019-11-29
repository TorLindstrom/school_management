package se.lexicon.tor.dao;

import se.lexicon.tor.student.Student;

import java.util.List;

public interface StudentDAO
{
    Student saveStudent(Student student);
    Student findByEmail(String email);
    List<Student> findByName(String name);
    Student findById(int id);
    List<Student> findAll();
    boolean deleteStudent(Student student);
}
