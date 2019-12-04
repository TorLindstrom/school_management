package se.lexicon.tor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.lexicon.tor.course.Course;
import se.lexicon.tor.dao.CourseDaoList;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

public class CourseDaoListTest
{
    CourseDaoList courseDaoList;

    @Before
    public void init(){
        courseDaoList = new CourseDaoList();
    }

    @After
    public void reset(){
        courseDaoList.clear();
    }

    @Test
    public void save()
    {
        Course course = new Course();
        course.setCourseName("English");
        course.setId(3);
        courseDaoList.saveCourse(course);

        assertEquals(course,courseDaoList.findByCourseId(3));
    }

    @Test
    public void findByIdThere()
    {
        Course course = new Course();
        course.setCourseName("English");
        course.setId(3);
        Course course2 = new Course();
        course2.setCourseName("geo");
        course2.setId(2);
        Course course3 = new Course();
        course3.setCourseName("chem");
        course3.setId(1);
        courseDaoList.saveCourse(course);
        courseDaoList.saveCourse(course2);
        courseDaoList.saveCourse(course3);

        assertEquals(course,courseDaoList.findByCourseId(3));
        assertEquals(course2,courseDaoList.findByCourseId(2));
        assertEquals(course3,courseDaoList.findByCourseId(1));
    }

    @Test
    public void findByIdNotThere()
    {
        Course course = new Course();
        course.setCourseName("English");
        course.setId(3);
        Course course2 = new Course();
        course2.setCourseName("geo");
        course2.setId(2);
        Course course3 = new Course();
        course3.setCourseName("chem");
        course3.setId(1);
        courseDaoList.saveCourse(course);
        courseDaoList.saveCourse(course2);
        courseDaoList.saveCourse(course3);

        assertNull(courseDaoList.findByCourseId(4));
        assertNull(courseDaoList.findByCourseId(20));
    }

    @Test
    public void findByNameThere()
    {
        Course course = new Course();
        course.setCourseName("English");
        course.setId(3);
        Course course2 = new Course();
        course2.setCourseName("geo");
        course2.setId(2);
        Course course3 = new Course();
        course3.setCourseName("chem");
        course3.setId(1);
        courseDaoList.saveCourse(course);
        courseDaoList.saveCourse(course2);
        courseDaoList.saveCourse(course3);

        assertTrue(courseDaoList.findByName("English").indexOf(course) > -1);
        assertTrue(courseDaoList.findByName("geo").indexOf(course2) > -1);
        assertTrue(courseDaoList.findByName("chem").indexOf(course3) > -1);
    }

    @Test
    public void findByNameNotThere()
    {
        Course course = new Course();
        course.setCourseName("English");
        course.setId(3);
        Course course2 = new Course();
        course2.setCourseName("geo");
        course2.setId(2);
        Course course3 = new Course();
        course3.setCourseName("chem");
        course3.setId(1);
        courseDaoList.saveCourse(course);
        courseDaoList.saveCourse(course2);
        courseDaoList.saveCourse(course3);

        assertNull(courseDaoList.findByName("engrish"));
        assertNull(courseDaoList.findByName("politics"));
        assertNull(courseDaoList.findByName("spacestuff"));
    }

    @Test
    public void findAll()
    {
        Course course = new Course();
        course.setCourseName("English");
        course.setId(3);
        Course course2 = new Course();
        course2.setCourseName("geo");
        course2.setId(2);
        Course course3 = new Course();
        course3.setCourseName("chem");
        course3.setId(1);
        courseDaoList.saveCourse(course);
        courseDaoList.saveCourse(course2);
        courseDaoList.saveCourse(course3);

        assertEquals(3, courseDaoList.findAll().size());
    }

    @Test
    public void removeCourseThere()
    {
        Course course = new Course();
        course.setCourseName("English");
        course.setId(3);
        Course course2 = new Course();
        course2.setCourseName("geo");
        course2.setId(2);
        Course course3 = new Course();
        course3.setCourseName("chem");
        course3.setId(1);
        courseDaoList.saveCourse(course);
        courseDaoList.saveCourse(course2);
        courseDaoList.saveCourse(course3);

        assertTrue(courseDaoList.removeCourse(course));
        assertTrue(courseDaoList.removeCourse(course3));
    }

    @Test
    public void removeCourseNotThere()
    {
        Course course = new Course();
        course.setCourseName("English");
        course.setId(3);
        Course course2 = new Course();
        course2.setCourseName("geo");
        course2.setId(2);
        Course course3 = new Course();
        course3.setCourseName("chem");
        course3.setId(1);
        Course course4 = new Course();
        courseDaoList.saveCourse(course);
        courseDaoList.saveCourse(course2);
        courseDaoList.saveCourse(course3);

        assertFalse(courseDaoList.removeCourse(course4));
    }

    @Test
    public void findByDateThere()
    {
        LocalDate localDate = LocalDate.of(2019, 12, 30);
        Course course = new Course();
        course.setCourseName("English");
        course.setStartDate(localDate);
        course.setId(3);
        Course course2 = new Course();
        course2.setCourseName("geo");
        course2.setId(2);
        course2.setStartDate(LocalDate.of(2020, Month.JANUARY, 20));
        Course course3 = new Course();
        course3.setCourseName("chem");
        course3.setStartDate(LocalDate.now());
        course3.setId(1);
        courseDaoList.saveCourse(course);
        courseDaoList.saveCourse(course2);
        courseDaoList.saveCourse(course3);

        assertTrue(courseDaoList.findByDate(localDate).contains(course));
        assertTrue(courseDaoList.findByDate(LocalDate.of(2020, Month.JANUARY, 20)).contains(course2));
        assertTrue(courseDaoList.findByDate(LocalDate.of(2020, 1, 20)).contains(course2));
        assertTrue(courseDaoList.findByDate(LocalDate.now()).contains(course3));
    }
}


