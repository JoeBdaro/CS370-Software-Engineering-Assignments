package edu.qc.seclass.replace;

import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;


public class MyMainTest {

    private ByteArrayOutputStream outStream;
    private ByteArrayOutputStream errStream;
    private PrintStream outOrig;
    private PrintStream errOrig;
    private Charset charset = StandardCharsets.UTF_8;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        errStream = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(errStream);
        outOrig = System.out;
        errOrig = System.err;
        System.setOut(out);
        System.setErr(err);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(outOrig);
        System.setErr(errOrig);
    }

    // Some utilities

    //creates tempFIle
    private File createTmpFile() throws IOException {
        File tmpfile = temporaryFolder.newFile();
        tmpfile.deleteOnExit();
        return tmpfile;
    }

    //creates testfile1
    private File createInputFile1() throws Exception {
        File file1 = createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!");

        fileWriter.close();
        return file1;
    }

    //creates testfile2
    private File createInputFile2() throws Exception {
        File file1 = createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice");

        fileWriter.close();
        return file1;
    }

    //creates testfile3
    private File createInputFile3() throws Exception {
        File file1 = createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123");

        fileWriter.close();
        return file1;
    }

    //creates testfile4
    private File createInputFile4() throws Exception {
        File file1 = createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Howdy Howdy\n" +
                "Howdy Howdy Howdy" +
                "Howdy Howdy Howdy\n" +
                "Howdy Howdy Howdy");

        fileWriter.close();
        return file1;
    }


    //gets content of files
    private String getFileContent(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    //======================================= Actual test cases=========================================================

    //Implementation of test frame #5
    @Test(expected = FileNotFoundException.class)
    public void myMainTest1() throws Exception {

        String args[] = {"Howdy", "How are you", "--", "" };
        Main.main(args);
    }

    //Implementation of test frame #12
    @Test
    public void myMainTest2() throws Exception {

        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-f", "-i", "Howdy", "How are you William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "How are you William Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String expected2 = "How are you William Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    //Implementation of test frame #2
    @Test
    public void myMainTest3()throws Exception{

        File inputFile1 = createInputFile1();

        String args[] = {"belligerent", "dumb", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("These Strings differ", expected1, actual1);
    }

    //Implementation of test frame #20
    @Test
    public void myMainTest4()throws Exception{

        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();
        File inputFile3 = createInputFile3();

        String args[] = {"-l", "i", "howdy", "goodbye", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"goodbye bill\" again!";

        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"goodbye Bill\" twice";

        String expected3 = "goodbye Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123";


        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());
        String actual3 = getFileContent(inputFile2.getPath());

        assertEquals("These Strings differ", expected1, actual1);
        assertEquals("These Strings differ", expected2, actual2);
        assertEquals("These strings differ", expected3, actual3);


    }

    //Implementation of test frame #4
    @Test
    public void myMainTest5()throws Exception{

        File inputFile1 = createInputFile1();

        String args[] = {"howdy", "goodbye", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "goodbye Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"goodbye bill\" again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("These strings differ", expected1, actual1);

    }


    //Implementation of test frame #9
    @Test
    public void myMainTest6()throws Exception{
        File inputFile1 = createInputFile1();

        String args[] = {"", "goodbye", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("These strings differ", expected1, actual1);


    }

    //Implementation of test frame #22
    @Test
    public void myMainTest7() throws Exception{

        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();
        File inputFile3 = createInputFile3();

        String args[] = {"-b", "-i", "howdy", "$", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
        Main.main(args);

        String expected1 = "$ Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"$ bill\" again!";

        String expected2 = "$ Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"$ Bill\" twice";

        String expected3 = "$ Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());
        String actual3 = getFileContent(inputFile3.getPath());

        assertEquals("Those strings differ", expected1, actual1);
        assertEquals("Those strings differ", expected2, actual2);
        assertEquals("Those strings differ", expected3, actual3);

        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));

    }

    //Implementation of test frame #41
    @Test
    public void myMainTest8() throws Exception{

        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();
        File inputFile3 = createInputFile3();

        String args[] = {"-b", "-i", "howdy Bill", "Hello William", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
        Main.main(args);

        String expected1 = "Hello William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"Hello William\" again!";

        String expected2 = "Hello William,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"Hello William\" twice";

        String expected3 = "Hello William, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());
        String actual3 = getFileContent(inputFile3.getPath());

        assertEquals("Those strings differ", expected1, actual1);
        assertEquals("Those strings differ", expected2, actual2);
        assertEquals("Those strings differ", expected3, actual3);

        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));

    }

    //Implementation of test frame #3
    @Test
    public void myMainTest9() throws Exception{


        File inputFile1 = createInputFile1();

        String args[] = {"-l", "-i", "Howdy", "bye", "--", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"bye bill\" again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("Those strings differ", expected1, actual1);

    }

    //Implementation of test frame #7
    @Test
    public void myMainTest10() throws Exception{

        File inputFile1 = createInputFile1();

        String args[] = {"-l", "-i", "Howdy", "bye", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"bye bill\" again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("Those strings differ", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath())));
    }


    //Newly created test case. Purpose: checks all four OPT operations especially that only the first and last occurrence only changes that way -f and -l works together on multiple files
    @Test
    public void myMainTest11() throws Exception{

        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();
        File inputFile3 = createInputFile3();

        String args[] = {"-b", "-i", "-f", "-l", "a", "burgundy", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is burgundy test file for the replace utility\n" +
                "Let's make sure it has at least burgundy few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String expected3 = "Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());
        String actual3 = getFileContent(inputFile3.getPath());

        assertEquals("Those strings differ", expected1, actual1);
        assertEquals("Those strings differ", expected2, actual2);
        assertEquals("Those strings differ", expected3, actual3);

        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
    }

    //Newly created test case. Purpose: check the -b OPT function
    @Test
    public void myMaintTest12() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();
        File inputFile3 = createInputFile3();

        String args[] = {"-b", "Bill", "Thomas", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
        Main.main(args);

        String expected1 = "Howdy Thomas,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String expected2 = "Howdy Thomas,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Thomas\" twice";

        String expected3 = "Howdy Thomas, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());
        String actual3 = getFileContent(inputFile3.getPath());

        assertEquals("Those strings differ", expected1, actual1);
        assertEquals("Those strings differ", expected2, actual2);
        assertEquals("Those strings differ", expected3, actual3);

        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));

    }

    //Newly created test case. Purpose: check the -f OPT function
    @Test
    public void myMainTest13() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();
        File inputFile3 = createInputFile3();

        String args[] = {"-f", "a", "REPLACED", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is REPLACED test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains REPLACED list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String expected3 = "Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());
        String actual3 = getFileContent(inputFile3.getPath());

        assertEquals("Those strings differ", expected1, actual1);
        assertEquals("Those strings differ", expected2, actual2);
        assertEquals("Those strings differ", expected3, actual3);
    }

    //Newly created test case. Purpose: check the -l OPT function
    @Test
    public void myMainTest14() throws Exception {
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();
        File inputFile3 = createInputFile3();

        String args[] = {"-l", "howdy", "Welcome", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"Welcome bill\" again!";

        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"Welcome Bill\" twice";

        String expected3 = "Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());
        String actual3 = getFileContent(inputFile3.getPath());

        assertEquals("Those strings differ", expected1, actual1);
        assertEquals("Those strings differ", expected2, actual2);
        assertEquals("Those strings differ", expected3, actual3);
    }

    //Newly created test case. Purpose: check the -i OPT function
    @Test
    public void myMainTest15() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();
        File inputFile3 = createInputFile3();

        String args[] = {"-i", "hoWdY", "Welcome", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
        Main.main(args);

        String expected1 = "Welcome Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"Welcome bill\" again!";

        String expected2 = "Welcome Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"Welcome Bill\" twice";

        String expected3 = "Welcome Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());
        String actual3 = getFileContent(inputFile3.getPath());

        assertEquals("Those strings differ", expected1, actual1);
        assertEquals("Those strings differ", expected2, actual2);
        assertEquals("Those strings differ", expected3, actual3);
    }

    //Newly created test case. Purpose: check the -f -l -i  OPT functions. checks if only the first and last instance of howdy while be replaced regardless of case
    @Test
    public void myMainTest16() throws Exception{
        File inputFile4 = createInputFile4();


        String args[] = {"-f", "-i", "-l","HoWdY", "REPLACED", "--", inputFile4.getPath()};
        Main.main(args);

        String expected1 = "REPLACED Howdy Howdy\n" +
                "Howdy Howdy Howdy" +
                "Howdy Howdy Howdy\n" +
                "Howdy Howdy REPLACED";

        String actual1 = getFileContent(inputFile4.getPath());

        assertEquals("Those strings differ", expected1, actual1);
    }

    //Newly created test case. Purpose: check the -f -b OPT functions if they work together
    @Test
    public void myMainTest17() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-b", "-f", "howdy", "REPLACED", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"REPLACED bill\" again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("Those strings differ", expected1, actual1);

        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }

    //Newly created test case. Purpose: check the -f -i OPT functions if they work together
    @Test
    public void myMainTest18() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-f", "-i", "howdy", "REPLACED", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "REPLACED Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("Those strings differ", expected1, actual1);
    }

    //Newly created test case. Purpose: check the -f -l OPT functions if they work together
    @Test
    public void myMainTest19() throws Exception{
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile4();
        String args[] = {"-f", "-l", "Howdy", "REPLACED", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "REPLACED Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String expected2 = "REPLACED Howdy Howdy\n" +
                "Howdy Howdy Howdy" +
                "Howdy Howdy Howdy\n" +
                "Howdy Howdy REPLACED";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("Those strings differ", expected1, actual1);
        assertEquals("Those strings differ", expected2, actual2);

    }

    //Newly created test case. Purpose: check the -f -b OPT functions if they work together
    @Test
    public void myMainTest20() throws Exception{
        File inputFile1 = createInputFile1();

        String args[] = {"-f", "-b", "howdy", "REPLACED", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"REPLACED bill\" again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("Those strings differ", expected1, actual1);

        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }

    //Newly created test case. Purpose: check the -l -i OPT functions if they work together
    @Test
    public void myMainTest21() throws Exception{
        File inputFile1 = createInputFile1();

        String args[] = {"-l", "-i", "howdy", "REPLACED", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "REPLACED Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("Those strings differ", expected1, actual1);
    }

    //Newly created test case. Purpose: check the -l -b OPT functions if they work together
    @Test
    public void myMainTest22() throws Exception{
        File inputFile1 = createInputFile1();

        String args[] = {"-l", "-b", "howdy", "REPLACED", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"REPLACED bill\" again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("Those strings differ", expected1, actual1);

        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

    }

    //Newly created test case. Purpose: check the -i -b OPT functions if they work together
    @Test
    public void myMainTest23() throws Exception{
        File inputFile1 = createInputFile1();

        String args[] = {"-i", "-b", "HoWdY", "REPLACED", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "REPLACED Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"REPLACED bill\" again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("Those strings differ", expected1, actual1);

        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }

    //Newly created test case. Purpose: check the -f -l -b OPT functions if they work together
    @Test
    public void myMainTest24() throws Exception{
        File inputFile1 = createInputFile2();

        String args[] = {"-f", "-l", "-b", "howdy", "REPLACED", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"REPLACED Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("Those strings differ", expected1, actual1);

        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }

    //Newly created test case. Purpose: check the -l -i -b OPT functions if they work together
    @Test
    public void myMainTest25() throws Exception{
        File inputFile1 = createInputFile1();

        String args[] = {"-l", "-i", "b", "HoWDy", "REPLACED", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"REPLACED bill\" again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("Those strings differ", expected1, actual1);

        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }

    //Newly created test case. Purpose: check the -f -i -b OPT functions if they work together
    @Test
    public void myMainTest26() throws Exception{
        File inputFile1 = createInputFile1();

        String args[] = {"-f", "-i", "b", "HoWDy", "REPLACED", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "REPLACED Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("Those strings differ", expected1, actual1);

        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }

    //Newly created test case. Purpose: safeguard against empty command
    @Test(expected = NullPointerException.class)
    public void myMainTest27() throws Exception{

        String args[] = {};
        Main.main(args);
    }

    //Newly created test case. Purpose: tests to remove whitespaces
    @Test
    public void myMainTest28() throws Exception{
        File inputFile1 = createInputFile1();

        String args[] = {" ", "", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "HowdyBill,\n" +
                "Thisisatestfileforthereplaceutility\n" +
                "Let'smakesureithasatleastafewlines\n" +
                "sothatwecancreatesomeinterestingtestcases...\n" +
                "Andlet'ssay\"howdybill\"again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("Those strings differ", expected1, actual1);

    }

    //Newly created test case. Purpose: test a different permutation of -b -f OPT operations
    @Test
    public void myMainTest29() throws Exception{
        File inputFile1 = createInputFile1();

        String args[] = {"-b", "-f", "Howdy", "REPLACED", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "REPLACED Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("Those strings differ", expected1, actual1);

        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

    }

    //Newly created test case. Purpose: test a different permutation of -b -f OPT operations
    @Test
    public void myMainTest30() throws Exception{
        File inputFile1 = createInputFile1();

        String args[] = {"-l", "-f", "a", "REPLACED", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is REPLACED test file for the replace utility\n" +
                "Let's make sure it has at least REPLACED few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("Those strings differ", expected1, actual1);

    }

    //Newly created test case. Purpose: test a different permutation of -i -f OPT operations
    @Test
    public void myMainTest31() throws Exception{
        File inputFile1 = createInputFile1();

        String args[] = {"-i", "-f", "HoWdY", "REPLACE", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "REPLACE Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("Those strings differ", expected1, actual1);

    }

    //Newly created test case. Purpose: test a different permutation of -b -f OPT operations
    @Test
    public void myMainTest32() throws Exception{
        File inputFile1 = createInputFile1();

        String args[] = {"-b", "-f", "howdy", "REPLACE", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"REPLACE bill\" again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("Those strings differ", expected1, actual1);

        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

    }

    //Newly created test case. Purpose: test a different permutation of -i -l OPT operations
    @Test
    public void myMainTest33() throws Exception{
        File inputFile1 = createInputFile1();

        String args[] = {"-i", "-l", "hOwDy", "REPLACE", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"REPLACE bill\" again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("Those strings differ", expected1, actual1);

    }

    //Newly created test case. Purpose: test a different permutation of -b -l OPT operations
    @Test
    public void myMainTest34() throws Exception{
        File inputFile1 = createInputFile1();

        String args[] = {"-b", "-l", "Howdy", "REPLACE", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "REPLACE Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("Those strings differ", expected1, actual1);

        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

    }

    //Newly created test case. Purpose: test a different permutation of -b -i OPT operations
    @Test
    public void myMainTest35() throws Exception{
        File inputFile1 = createInputFile1();

        String args[] = {"-b", "-i", "HoWdY", "REPLACE", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "REPLACE Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"REPLACE bill\" again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("Those strings differ", expected1, actual1);

        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

    }

    //Newly created test case. Purpose: test a different permutation of -i -l -f OPT operations
    @Test
    public void myMainTest36() throws Exception{
        File inputFile1 = createInputFile1();

        String args[] = {"-i", "-l", "-f", "HoWdY", "REPLACE", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "REPLACE Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"REPLACE bill\" again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("Those strings differ", expected1, actual1);

    }

    //Newly created test case. Purpose: test a different permutation of -b -i -l OPT operations
    @Test
    public void myMainTest37() throws Exception{
        File inputFile1 = createInputFile1();

        String args[] = {"-b", "-i", "-l", "HoWdY", "REPLACE", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"REPLACE bill\" again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("Those strings differ", expected1, actual1);

        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

    }

    //Newly created test case. Purpose: test a different permutation of -b -i -l OPT operations
    @Test
    public void myMainTest38() throws Exception{
        File inputFile1 = createInputFile1();

        String args[] = {"-b", "-i", "-f", "HoWdY", "REPLACE", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "REPLACE Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("Those strings differ", expected1, actual1);

        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

    }

    //Newly created test case. Purpose: test a different permutation of -i -f -b OPT operations
    @Test
    public void myMainTest39() throws Exception{
        File inputFile1 = createInputFile1();

        String args[] = {"-i", "-f", "-b", "HoWdY", "REPLACE", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "REPLACE Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("Those strings differ", expected1, actual1);

        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

    }

    //Newly created test case. Purpose: test a different permutation of -i -l -b OPT operations
    @Test
    public void myMainTest40() throws Exception{
        File inputFile1 = createInputFile1();

        String args[] = {"-i", "-l", "-b", "HoWdY", "REPLACE", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"REPLACE bill\" again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("Those strings differ", expected1, actual1);

        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

    }
}