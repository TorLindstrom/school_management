package se.lexicon.tor.dao;

import se.lexicon.tor.course.Course;

import java.time.LocalDate;
import java.util.List;

public interface CourseDAO
{
    Course saveCourse(Course course);
    Course findByCourseId(int id);
    List<Course> findByName(String name);
    List<Course> findByDate(LocalDate date);
    List<Course> findAll();
    boolean removeCourse(Course course);
}
