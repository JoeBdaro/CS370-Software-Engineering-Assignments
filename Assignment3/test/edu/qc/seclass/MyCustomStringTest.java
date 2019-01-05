package edu.qc.seclass;

import org.junit.*;


import static org.junit.Assert.*;

public class MyCustomStringTest {

    private MyCustomStringInterface mycustomstring;

    @Before
    public void setUp() {
        mycustomstring = new MyCustomString();
    }

    @After
    public void tearDown() {
        mycustomstring = null;
    }

    //tests the testcount method
    @Test
    public void testCountNumbers1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals(7, mycustomstring.countNumbers());
    }
    //tests that the program throws nullpointerexception
    @Test (expected = NullPointerException.class )
    public void testCountNumbers2() {
        mycustomstring.setString(null);
        mycustomstring.countNumbers();
    }

    @Test
    public void testCountNumbers3() {
        mycustomstring.setString("");
        assertEquals(0, mycustomstring.countNumbers());
    }

    //checks if a number with a space is treated as one number or two numbers, should be two numbers
    @Test
    public void testCountNumbers4() {
        mycustomstring.setString("Check if 6 1 is treated");
        assertEquals(2, mycustomstring.countNumbers());
    }

    //tests to see if 645 is a seperate num from 456 and 32 and 1
    @Test
    public void testCountNumbers5() {
        mycustomstring.setString("check if 645 456 32 1");
        assertEquals(4, mycustomstring.countNumbers());
    }

    @Test
    public void testCountNumbers6() {
        mycustomstring.setString("hello there are no numbers !");
        assertEquals(0, mycustomstring.countNumbers());
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("d33p md1  i51,it", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, false));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd2() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("'bt t0 6snh r6rh", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, true));
    }
    //tests the illegalargumentexception
    @Test (expected = IllegalArgumentException.class )
    public void testGetEveryNthCharacterFromBeginningOrEnd3() {
        mycustomstring.setString("Let's test this exception");
        mycustomstring.getEveryNthCharacterFromBeginningOrEnd(0, false);
    }
    //tests the NullPointerexception
    @Test (expected = NullPointerException.class )
    public void testGetEveryNthCharacterFromBeginningOrEnd4() {
        mycustomstring.setString(null);
        mycustomstring.getEveryNthCharacterFromBeginningOrEnd(1, false);
    }

    //simple test to compare the results of this test to the next one
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd5() {
        mycustomstring.setString("hello world");
        assertEquals("el ol", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(2, false));
    }
    //simple test that starting from end usually nets the same result as starting from beginning but isn't always true
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd6() {
        mycustomstring.setString("hello world");
        assertEquals("el ol", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(2, true));
    }
    //test that proves starting from end and begining of the string can net two different outcomes
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd7() {
        mycustomstring.setString("random l3t3rz  ar3 not tooo hard too wri!te");
        assertEquals("nm3r 3otoa ort", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, false));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd8() {
        mycustomstring.setString("random l3t3rz  ar3 not tooo hard too wri!te");
        assertEquals("aol3 rn ohdow!", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, true));
    }

    //test to print out the entire string by itteration
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd9() {
        mycustomstring.setString("Print me out completley");
        assertEquals("Print me out completley", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(1, false));
    }

    //test to print out the itteration by starting from the end and reordering
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd10() {
        mycustomstring.setString("Print me out completley");
        assertEquals("Print me out completley", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(1, true));
    }

    //tests the exception once more where n = -1
    @Test (expected = IllegalArgumentException.class )
    public void testGetEveryNthCharacterFromBeginningOrEnd11() {
        mycustomstring.setString("Let's test this exception");
        mycustomstring.getEveryNthCharacterFromBeginningOrEnd(-1, false);
    }

    //to make sure that the outcome is empty if n is bigger than the string length
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd12() {
        mycustomstring.setString("helloRandom");
        assertEquals("", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(12, true));
    }

    //prints out only spaces
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd13() {
        mycustomstring.setString("1 2 3 4 5 6 7 8 9 ");
        assertEquals("         ", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(2, false));
    }

    //prints out only the numbers
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd14() {
        mycustomstring.setString("1 2 3 4 5 6 7 8 9 ");
        assertEquals("123456789", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(2, true));
    }

    //test case for converting digits to string
    @Test
    public void testConvertDigitsToNamesInSubstring1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mycustomstring.convertDigitsToNamesInSubstring(17, 23);
        assertEquals("I'd b3tt3r put sZerome dOneSix1ts in this 5tr1n6, right?", mycustomstring.getString());
    }
    //tests the illegalArgumentException by making startingpoint > endingpoint
    @Test (expected = IllegalArgumentException.class)
    public void testConvertDigitsToNamesInSubstring2() {
        mycustomstring.setString("random test ");
        mycustomstring.convertDigitsToNamesInSubstring(12,9);
    }
    //tests if index out of bounds exception for starting point
    @Test (expected = MyIndexOutOfBoundsException.class)
    public void testConvertDigitsToNamesInSubstring3() {
        mycustomstring.setString("random test ");
        mycustomstring.convertDigitsToNamesInSubstring(0,9);
    }

    //tests if index out of bounds exception for Ending point
    @Test (expected = MyIndexOutOfBoundsException.class)
    public void testConvertDigitsToNamesInSubstring4() {
        mycustomstring.setString("random test ");
        mycustomstring.convertDigitsToNamesInSubstring(1,mycustomstring.getString().length() + 1);
    }

    //tests Nullpointer exception by making both starting and endpoints 0
    @Test (expected = NullPointerException.class)
    public void testConvertDigitsToNamesInSubstring5() {
        mycustomstring.setString(null);
        mycustomstring.convertDigitsToNamesInSubstring(1,2);
    }
    //Tests to make sure a space would work
    @Test
    public void testConvertDigitsToNamesInSubstring6() {
        mycustomstring.setString(" ");
        mycustomstring.convertDigitsToNamesInSubstring(1,1);
    }
    //Random rigorous test to see if the method fails
    @Test
    public void testConvertDigitsToNamesInSubstring7() {
        mycustomstring.setString("random69 36 te4st ");
        mycustomstring.convertDigitsToNamesInSubstring(1, 15);
        assertEquals("randomSixNine ThreeSix teFourst ", mycustomstring.getString());
    }

    //tests the method with special keys
    @Test
    public void testConvertDigitsToNamesInSubstring8() {
        mycustomstring.setString("random6$9 3-6 te4st ");
        mycustomstring.convertDigitsToNamesInSubstring(1, 15);
        assertEquals("randomSix$Nine Three-Six te4st ", mycustomstring.getString());
    }
}
