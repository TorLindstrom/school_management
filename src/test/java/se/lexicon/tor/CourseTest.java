package se.lexicon.tor;

import org.junit.Before;
import org.junit.Test;
import se.lexicon.tor.course.Course;

import static org.junit.Assert.*;

public class CourseTest
{
    Course course;

    @Before
    public void init(){
        course = new Course();
    }

    @Test
    public void register()
    {
        se.lexicon.tor.student.Student student = new se.lexicon.tor.student.Student();
        student.setName("Tor");
        course.register(student);

        assertTrue(course.getStudents().indexOf(student) > -1);
    }

    @Test
    public void unregister()
    {
        se.lexicon.tor.student.Student student = new se.lexicon.tor.student.Student();
        student.setName("Tor");
        se.lexicon.tor.student.Student student2 = new se.lexicon.tor.student.Student();
        student.setName("Tor2");
        course.register(student);
        course.register(student2);
        course.unregister(student);

        assertEquals(-1, course.getStudents().indexOf(student));
    }

    @Test
    public void setIdValid()
    {
        course.setId(1);

        assertEquals(1, course.getId());
    }

    @Test
    public void setIdInvalid()
    {
        course.setId(-3);

        assertEquals(0, course.getId());
    }
}
