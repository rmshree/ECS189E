/**
 * Created by Mahashree on 3/8/2017.
 */

import api.IAdmin;
import api.IInstructor;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Instructor;
import api.core.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestStudent {

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

        this.student.registerForClass("Hisname", "Name", 2018);
        this.student.registerForClass("Hername", "Name", 2018);

        this.instructor.addHomework("Mahi", "Name",2018, "firstHomework", "Description");
        this.instructor.addHomework("Mahi", "Name",2017, "firstHomework", "Description");
        this.student.registerForClass("Hisname", "Name", 2017);

    }

    @Test
    public void testMakeClass() {
        // A proper class that is created
        assertTrue(this.admin.classExists("Name", 2018));
    }

    @Test
    public void testMakeClass2() {
        // Registering for a class that has open seats and not at full capacity.
        this.student.registerForClass("NewStudent", "Name", 2018);
        this.student.registerForClass("NewerStudent", "Name", 2018);
        assertTrue(this.admin.classExists("Name", 2018));
        assertTrue(this.student.isRegisteredFor("NewerStudent","Name", 2018));
    }

    @Test
    public void testMakeClass3() {
        // Registering for a class that has no open seats and is at full capacity.
        this.student.registerForClass("NewStudent", "Name", 2018);
        this.student.registerForClass("NewerStudent", "Name", 2018);
        this.student.registerForClass("NewerStudent1", "Name", 2018);
        this.student.registerForClass("NewerStudent2", "Name", 2018);

        assertTrue(this.admin.classExists("Name", 2018));
        assertFalse(this.student.isRegisteredFor("NewerStudent2", "Name", 2018));
    }
    @Test
    public void testMakeClass4() {
        //checking dropclass if student registered for class
        this.student.dropClass("Hisname", "Name", 2018);
        assertFalse(this.student.isRegisteredFor("Hisname", "Name", 2018));
    }
    @Test
    public void testMakeClass5() {
        //checking dropclass if student is not registered for class
        this.student.dropClass("Hisname1", "Name", 2018);
        assertFalse(this.student.isRegisteredFor("Hisname1", "Name", 2018));
    }

    @Test
    public void testMakeClass6() {
        //provided homework exists, and student is not registered and class taught in current year.
        this.student.submitHomework("Hername","fisrtHomework", "Answer","Name", 2017);
        assertTrue(this.instructor.homeworkExists("Name",2017,"firstHomework"));
        assertFalse(this.student.isRegisteredFor("Hername","Name", 2017));
        assertFalse(this.student.hasSubmitted("Hername", "firstHomework","Name",2017));

    }

    @Test
    public void testMakeClass7() {
        //provided homework exists, and student is registered and class not taught in current year.
        this.student.submitHomework("Hername","fisrtHomework", "Answer","Name", 2018);
        assertTrue(this.instructor.homeworkExists("Name",2018,"firstHomework"));
        assertTrue(this.student.isRegisteredFor("Hername","Name", 2018));
        assertFalse(this.student.hasSubmitted("Hername", "firstHomework","Name",2018));

    }
    @Test
    public void testMakeClass8() {
        //homework does not exist, and student is registered and class is taught in current year.
        this.student.submitHomework("Hisname","secondHomework", "Answer","Name", 2017);
        assertFalse(this.instructor.homeworkExists("Name",2017,"secondHomework"));
        assertTrue(this.student.isRegisteredFor("Hisname","Name", 2017));
        assertFalse(this.student.hasSubmitted("Hisname", "secondHomework","Name",2017));

    }
}


