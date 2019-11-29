package se.lexicon.tor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.lexicon.tor.student.Student;
import se.lexicon.tor.dao.StudentDaoList;

import static org.junit.Assert.*;

public class StudentDaoListTest
{
    StudentDaoList studentDaoList;

    @Before
    public void init(){
        studentDaoList = new StudentDaoList();
    }

    @After
    public void reset(){
        studentDaoList.clear();
    }

    @Test
    public void save()
    {
        Student student = new Student();
        student.setName("Tor");
        student.setId(2);

        studentDaoList.saveStudent(student);

        assertEquals(student, studentDaoList.findById(2));
    }

    @Test
    public void findByNameThere()
    {
        Student student = new Student();
        student.setName("Tor");
        student.setId(2);
        Student student2 = new Student();
        student2.setName("Otur");
        student2.setId(3);
        Student student3 = new Student();
        student3.setName("Tortilla");
        student3.setId(4);

        studentDaoList.saveStudent(student);
        studentDaoList.saveStudent(student2);
        studentDaoList.saveStudent(student3);

        assertEquals(1, studentDaoList.findByName("Tor").size());
    }

    @Test
    public void findByNameNotThere()
    {
        Student student = new Student();
        student.setName("Tor");
        student.setId(2);
        Student student2 = new Student();
        student2.setName("Otur");
        student2.setId(3);
        Student student3 = new Student();
        student3.setName("Tortilla");
        student3.setId(4);

        studentDaoList.saveStudent(student);
        studentDaoList.saveStudent(student2);
        studentDaoList.saveStudent(student3);

        assertNull(studentDaoList.findByName("Turkey"));
    }

    @Test
    public void findByIdThere()
    {
        Student student = new Student();
        student.setName("Tor");
        student.setId(2);
        Student student2 = new Student();
        student2.setName("Otur");
        student2.setId(3);
        Student student3 = new Student();
        student3.setName("Tortilla");
        student3.setId(4);

        studentDaoList.saveStudent(student);
        studentDaoList.saveStudent(student2);
        studentDaoList.saveStudent(student3);

        assertEquals(student, studentDaoList.findById(2));
        assertEquals(student3, studentDaoList.findById(4));
    }

    @Test
    public void findAll()
    {
        Student student = new Student();
        student.setName("Tor");
        student.setId(2);
        Student student2 = new Student();
        student2.setName("Otur");
        student2.setId(3);
        Student student3 = new Student();
        student3.setName("Tortilla");
        student3.setId(4);

        studentDaoList.saveStudent(student);
        studentDaoList.saveStudent(student2);
        studentDaoList.saveStudent(student3);

        assertEquals(3, studentDaoList.findAll().size());
    }

    @Test
    public void removeStudentThere()
    {
        Student student = new Student();
        student.setName("Tor");
        student.setId(2);
        Student student2 = new Student();
        student2.setName("Otur");
        student2.setId(3);
        Student student3 = new Student();
        student3.setName("Tortilla");
        student3.setId(4);

        studentDaoList.saveStudent(student);
        studentDaoList.saveStudent(student2);
        studentDaoList.saveStudent(student3);

        assertTrue(studentDaoList.deleteStudent(student));
        assertTrue(studentDaoList.deleteStudent(student2));
        assertTrue(studentDaoList.deleteStudent(student3));
        assertFalse(studentDaoList.deleteStudent(student3));
    }

    @Test
    public void removeStudentNotThere()
    {
        Student student = new Student();
        student.setName("Tor");
        student.setId(2);
        Student student2 = new Student();
        student2.setName("Otur");
        student2.setId(3);
        Student student3 = new Student();
        student3.setName("Tortilla");
        student3.setId(4);
        Student student4 = new Student();

        studentDaoList.saveStudent(student);
        studentDaoList.saveStudent(student2);
        studentDaoList.saveStudent(student3);

        assertFalse(studentDaoList.deleteStudent(student4));
    }

    @Test
    public void findByEmail()
    {
        Student student = new Student();
        student.setEmail("Tor@");
        student.setId(2);
        Student student2 = new Student();
        student2.setEmail("Otur@");
        student2.setId(3);
        Student student3 = new Student();
        student3.setEmail("Tortilla@");
        student3.setId(4);

        studentDaoList.saveStudent(student);
        studentDaoList.saveStudent(student2);
        studentDaoList.saveStudent(student3);

        assertEquals(student, studentDaoList.findByEmail("Tor@"));
        assertEquals(student2, studentDaoList.findByEmail("Otur@"));
        assertEquals(student3, studentDaoList.findByEmail("Tortilla@"));
        assertNull(studentDaoList.findByEmail("@"));
    }
}
