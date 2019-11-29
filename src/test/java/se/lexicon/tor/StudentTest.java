package se.lexicon.tor;

import org.junit.Before;
import se.lexicon.tor.course.Course;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StudentTest
{
    Course student;

    @Before
    public void init(){
        student = new Course();
    }
}
