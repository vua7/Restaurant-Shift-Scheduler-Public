package com.example.a395_team_ritchie;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class studentsTest {
    Students testStudents1;
    Weekday testWeekday1;
    Weekend testWeekend1;
    BusyWeekday testBusyWeekday1;
    BusyWeekend testBusyWeekend1;

    @Before
    public void createStudents() {
        testStudents1 = new Students();
        testWeekday1 = new Weekday();
        testWeekend1 = new Weekend();
        testBusyWeekday1 = new BusyWeekday();
        testBusyWeekend1 = new BusyWeekend();

    }

    @After

    @Test
    public void testStudents() {
        testStudents1.addStudent("John Doe");
        testStudents1.addStudent("Jane Doe");

        assert (testStudents1.getStudentTotal() == 2);

        testStudents1.removeStudent("John Doe");
        testStudents1.removeStudent("Jane Doe");

        assert (testStudents1.getStudentTotal() == 0);

        Students testStudents2 = new Students();
        testStudents2.addStudent("Other Joe");

        assertFalse (testStudents1 == testStudents2);

        Student testStudent1 = new Student();
        Student testStudent2 = new Student();

        testStudents1.addStudent("John Wick");
        testStudents1.addStudent("John Rambo");
        testStudent1 = testStudents1.getStudent("John Wick");
        testStudent2 = testStudents1.getStudent("John Rambo");

        testWeekday1.fillShift(testStudent1, "Morning");
        assert (testWeekday1.morningShiftFull() == false);

        testWeekday1.fillShift(testStudents2.getStudent("Other Joe"), "Morning");
        assert (testWeekday1.morningShiftFull() == true);

        testWeekend1.fillShift(testStudent1, "Full Day");
        assert (testWeekend1.isDayFull() == false);

        testWeekend1.fillShift(testStudents2.getStudent("Other Joe"), "Full Day");
        assert (testWeekend1.isDayFull() == true);

        testBusyWeekday1.fillShift(testStudent1, "Afternoon");
        testBusyWeekday1.fillShift(testStudent2, "Afternoon");
        assert (testBusyWeekday1.afternoonShiftFull() == false);

        testBusyWeekday1.fillShift(testStudents2.getStudent("Other Joe"), "Afternoon");
        assert (testBusyWeekday1.afternoonShiftFull() == true);

        testBusyWeekend1.fillShift(testStudent1, "Full Day");
        testBusyWeekend1.fillShift(testStudent2, "Full Day");
        assert (testBusyWeekend1.isDayFull() == false);

        testBusyWeekend1.fillShift(testStudents2.getStudent("Other Joe"), "Full Day");
        assert (testBusyWeekend1.isDayFull() == true);

        Month testMonth1 = new Month("October", "2020");
        testMonth1.addShift(testWeekday1);
        testMonth1.addShift(testWeekend1);
        testMonth1.addShift(testBusyWeekday1);
        testMonth1.addShift(testBusyWeekend1);

        System.out.println("--- DISPLAYING SHIFTS ---");
        testMonth1.printShifts();

        Student otherStudent1 = new Student();
        otherStudent1.setStudentName("Bill");
        otherStudent1.setStudentID(12345);
        otherStudent1.canOpen();

        Student otherStudent2 = new Student();
        otherStudent2.setStudentName("Bob");
        otherStudent2.setStudentID(67890);
        otherStudent2.canClose();

        System.out.println("--- DISPLAYING STUDENTS OPENING/CLOSING CAPABLE ---");
        System.out.println(otherStudent1);
        System.out.println(otherStudent2);

        System.out.println("--- DISPLAYING STUDENTS IN LIST ---");
        testStudents1.traverseStudents();

        testStudents1.removeStudent("John Wick");
        testStudents1.removeStudent("John Rambo");

        System.out.println("--- DISPLAYING STUDENTS IN LIST ---");
        testStudents1.traverseStudents();

    }

}