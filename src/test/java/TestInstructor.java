import api.IAdmin;
import api.IInstructor;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Instructor;
import api.core.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestInstructor {

    private IAdmin admin;
    private IStudent student;
    private IInstructor instructor;

    @Before
    public void setup() {
        this.admin = new Admin();
        this.student = new Student();
        this.instructor = new Instructor();

        this.admin.createClass("Name", 2018, "Mahi", 5);
        this.admin.createClass("Name", 2017, "Mahi", 5);

        this.student.registerForClass("Firoz", "Name", 2018);
        this.student.registerForClass("Arushi", "Name", 2017);
    }

    @Test
    public void testMakeClass() {
        // A proper class that is created
        assertTrue(this.admin.classExists("Name", 2018));
    }

    @Test
    public void testMakeClass2() {
        // Adding homework given all constraints are true.
        this.instructor.addHomework("Mahi", "Name", 2018, "firstHomework", "Description");
        assertTrue(this.instructor.homeworkExists("Name", 2017, "firstHomework"));
        assertFalse(this.instructor.homeworkExists("Name1", 2017, "firstHomework"));
    }

    @Test
    public void testMakeClass3() {
        // Checking submit homework and assign grade.
        this.student.submitHomework("Arushi", "fisrtHomework", "Answer", "Name", 2017);
        this.instructor.assignGrade("Mahi", "Name", 2017, "firstHomework", "Arushi", 90);
        int gradeWanted = 90;
        assertTrue(this.instructor.getGrade("Name", 2017, "firstHomework", "Arushi") == gradeWanted);
    }

    @Test
    public void testMakeClass4() {
        //Checking if homework exists.
        this.instructor.addHomework("Mahi", "Name", 2017, "secondHomework", "Description");
        assertTrue(this.instructor.homeworkExists("Name", 2017, "secondHomework"));
        assertFalse(this.instructor.homeworkExists("Name", 2017, "thirdHomework"));
    }

    @Test
    public void testMakeClass5() {
        //Assign grade without the student submitting
        this.instructor.assignGrade("Mahi", "Name", 2017, "firstHomework", "Firoz", 90);
        int gradeWanted = 90;
        assertFalse(this.instructor.getGrade("Name", 2018, "firstHomework", "Firoz") == gradeWanted);
    }

    @Test
    public void testMakeClass6 (){
        //Assign grade to student who does not exist
        this.instructor.assignGrade("Mahi", "Name", 2017, "firstHomework", "Nobody", 50);
        int gradeWanted = 50;
        assertFalse(this.instructor.getGrade("Name", 2017, "firstHomework", "Nobody") == gradeWanted);
    }

}

