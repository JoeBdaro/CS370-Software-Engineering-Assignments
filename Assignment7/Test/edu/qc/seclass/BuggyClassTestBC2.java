package edu.qc.seclass;

import org.junit.*;

import static org.junit.Assert.*;

public class BuggyClassTestBC2 {

    BuggyClass myBuggyClass;

    @Before
    public void setUp() {
        myBuggyClass= new BuggyClass();
    }

    @After            //after each test
    public void tearDown() {
        myBuggyClass = null;
    }

    //This test does 50%+ branch coverage and can only show the fault
    @Test
    public void buggyMethod2() {
        assertEquals(5, myBuggyClass.buggyMethod2(2, 6));
    }
}