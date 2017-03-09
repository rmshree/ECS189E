/**
 * Created by Mahashree on 3/8/2017
 */

import api.IAdmin;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class TestAdmin {

    private IAdmin admin;
    private IStudent student;

    @Before
    public void setup() {
        this.admin = new Admin();
        this.student = new Student();
        this.admin.createClass("Name", 2018, "Mahi", 5);
        this.student.registerForClass("Hisname", "Name", 2018);
        this.student.registerForClass("Hername", "Name", 2018);

    }

    @Test
    public void testMakeClass() {
        // A proper class that is created
        this.admin.createClass("Test", 2017, "Instructor", 15);
        assertTrue(this.admin.classExists("Test", 2017));
    }

    @Test
    public void testMakeClass2() {
        // A class cannot be earlier
        this.admin.createClass("Test", 2016, "Instructor", 15);
        assertFalse(this.admin.classExists("Test", 2016));
    }


    @Test
    public void testMakeClass3() {
        //Instructor can be assigned to more than 2 courses per year
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.admin.createClass("Tester", 2017, "Instructor", 15);
        this.admin.createClass("Testers", 2017, "Instructor", 15);

        assertFalse(this.admin.classExists("Testers", 2017));
    }
    @Test
    public void testMakeClass4() {
        //Maximum capacity of the class should be > 0
        this.admin.createClass("Test", 2017, "Instructor", 0);
        assertFalse(this.admin.classExists("Test", 2017));
    }


    @Test
    public void testMakeClass5() {
        // Testing that the class capacity can be more than enrolled students.
        this.admin.changeCapacity("Name", 2018, 3);
        assertEquals(this.admin.getClassCapacity("Name", 2018), 3);
    }

    @Test
    public void testMakeClass6() {
        //Checking class capacity can change to be less than the enrolled students.
        this.admin.changeCapacity("Name", 2018, 1);
        assertEquals(this.admin.getClassCapacity("Name", 2018), 5);
    }

}

