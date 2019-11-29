package se.lexicon.tor.dao;

import se.lexicon.tor.course.Course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoList implements CourseDAO
{
    private static List<Course> courses;

    public CourseDaoList()
    {
        courses = new ArrayList<>();
    }

    @Override
    public Course saveCourse(Course course)
    {
        courses.add(course);
        return course;
    }

    @Override
    public Course findByCourseId(int id)
    {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        return null;
    }

    @Override
    public List<Course> findByName(String name)
    {
        List<Course> named = new ArrayList<>();
        for (Course course : courses) {
            if (course.getCourseName().equals(name)) {
                named.add(course);
            }
        }
        if (named.isEmpty()) {
            return null;
        } else {
            return named;
        }
    }

    @Override
    public List<Course> findByDate(LocalDate date)
    {
        List<Course> named = new ArrayList<>();
        for (Course course : courses) {
            if (course.getStartDate() == (date)) {
                named.add(course);
            }
        }
        if (named.isEmpty()) {
            return null;
        } else {
            return named;
        }
    }

    @Override
    public List<Course> findAll()
    {
        return courses;
    }

    @Override
    public boolean removeCourse(Course course)
    {
        if (courses.indexOf(course) > -1) {
            courses.remove(course);
            return true;
        } else {
            return false;
        }
    }

    public void clear(){
        courses = new ArrayList<>();
    }
}
