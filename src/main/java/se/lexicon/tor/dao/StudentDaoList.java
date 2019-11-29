package se.lexicon.tor.dao;

import se.lexicon.tor.student.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoList implements StudentDAO
{
    private static List<Student> students;

    public StudentDaoList()
    {
        students = new ArrayList<>();
    }

    @Override
    public Student saveStudent(Student student)
    {
        students.add(student);
        return student;
    }

    @Override
    public Student findByEmail(String email)
    {
        for (Student student : students) {
            if (student.getEmail().equals(email)) {
                return student;
            }
        }
        return null;
    }

    @Override
    public List<Student> findByName(String name)
    {
        List<Student> named = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().equals(name)) {
                named.add(student);
            }
        }
        if (named.isEmpty()){
            return null;
        } else {
            return named;
        }
    }

    @Override
    public Student findById(int id)
    {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    @Override
    public List<Student> findAll()
    {
        return students;
    }

    @Override
    public boolean deleteStudent(Student student)
    {
        if (students.indexOf(student) > -1) {
            students.remove(student);
            return true;
        } else {
            return false;
        }
    }

    public void clear(){
        students = new ArrayList<>();
    }
}
