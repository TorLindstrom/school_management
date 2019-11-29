package se.lexicon.tor.course;

import se.lexicon.tor.student.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Course
{
    private int id;
    private String courseName;
    private LocalDate startDate;
    private int weekDuration;
    private List<se.lexicon.tor.student.Student> students;

    public Course(){
        students = new ArrayList<>();
    }

    public void register(se.lexicon.tor.student.Student student){
        students.add(student);
    }

    public void unregister(se.lexicon.tor.student.Student student){
        students.remove(student);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        if (id > -1) {
            this.id = id;
        }
    }

    public String getCourseName()
    {
        return courseName;
    }

    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public void setStartDate(LocalDate startDate)
    {
        this.startDate = startDate;
    }

    public int getWeekDuration()
    {
        return weekDuration;
    }

    public void setWeekDuration(int weekDuration)
    {
        this.weekDuration = weekDuration;
    }

    public List<se.lexicon.tor.student.Student> getStudents()
    {
        return students;
    }

    public void setStudents(List<se.lexicon.tor.student.Student> students)
    {
        this.students = students;
    }
}
