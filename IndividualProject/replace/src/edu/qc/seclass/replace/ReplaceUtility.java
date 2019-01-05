package edu.qc.seclass.replace;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

public class ReplaceUtility {

    String command;
    String[] commandBrokendown;

    String fromString;
    String changeToString;
    LinkedList<String> filenames = new LinkedList<String>();

    boolean firstOnlyFlag;
    boolean lastOnlyFlag;
    boolean caseInsensitiveFlag;
    boolean backupFlag;
    boolean passedTheDoubleDashes;
    boolean passedFromString;

    //Brings the command into the class to start the replace process
    public ReplaceUtility(String command) throws IOException {
        this.command = command;
        fromString = null;
        changeToString = null;
        firstOnlyFlag = false;
        lastOnlyFlag = false;
        caseInsensitiveFlag = false;
        backupFlag = false;
        passedTheDoubleDashes = false;
        passedFromString = false;
        parseCommand(this.command);

    }

    //Parses the command into an array to then starts setting opt flags and choosing what string will be replaced from what and to and what files to look for
    public void parseCommand(String command) throws IOException {
        commandBrokendown = command.split("\\s+");

        if (!commandBrokendown[0].equalsIgnoreCase("replace")) {
            usage();
        }

        for (int i = 1; i < commandBrokendown.length; i++) {

            if (commandBrokendown[i].equalsIgnoreCase("-i")) {
                caseInsensitiveFlag = true;
            }

            if (commandBrokendown[i].equalsIgnoreCase("-l")) {
                lastOnlyFlag = true;
            }

            if (commandBrokendown[i].equalsIgnoreCase("-f")) {
                firstOnlyFlag = true;
            }

            if (commandBrokendown[i].equalsIgnoreCase("-b")) {
                backupFlag = true;
            }

            if (passedTheDoubleDashes == true) {
                filenames.add(commandBrokendown[i]);
            }

            if (commandBrokendown[i].equalsIgnoreCase("--")) {
                passedTheDoubleDashes = true;
            }

            if ((!commandBrokendown[i].equalsIgnoreCase("-i")) && (!commandBrokendown[i].equalsIgnoreCase("-l")) && (!commandBrokendown[i].equalsIgnoreCase("-f")) && (!commandBrokendown[i].equalsIgnoreCase("-b")) && (passedFromString == false) && (passedTheDoubleDashes == false)) {
                fromString = commandBrokendown[i];
                passedFromString = true;
            }

            if ((!commandBrokendown[i].equalsIgnoreCase("-i")) && (!commandBrokendown[i].equalsIgnoreCase("-l")) && (!commandBrokendown[i].equalsIgnoreCase("-f")) && (!commandBrokendown[i].equalsIgnoreCase("-b")) && (passedFromString == true) && (passedTheDoubleDashes == false)) {
                changeToString = commandBrokendown[i];
            }

            if (i == (commandBrokendown.length - 1) && passedTheDoubleDashes == false) {
                usage();
            }

        }
        if (fromString == null || changeToString == null) {
            usage();
        }
        checkFilesExist();
    }

    private void usage() {
        System.err.println("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- " + "<filename> [<filename>]*");
    }

    //checks if the files that were requested exist

    public void checkFilesExist() throws IOException {
        for (int i = 0; i < filenames.size(); i++) {
            File f = new File(filenames.get(i));
            if (!f.isFile()) {
                usage();
            }
        }
        executeCommands();
    }

    public void executeCommands() throws IOException {

        if (backupFlag == true) {
            backupFile();
        }

        for (int i = 0; i < filenames.size(); i++) {

            //reads the file then places the contents in a variable string
            File fileToBeModified = new File(filenames.get(i));
            String fileContent = "";
            BufferedReader reader = new BufferedReader(new FileReader(filenames.get(i)));
            String line = reader.readLine();
            while (line != null) {
                fileContent = fileContent + line + System.lineSeparator();
                line = reader.readLine();
            }


            if (caseInsensitiveFlag == false && firstOnlyFlag == false && lastOnlyFlag == false) {
                //replaces the instance of the first string with the second
                String newFileContent = fileContent.replaceAll(fromString, changeToString);

                //locates the file I would like to edit and writes to it
                FileWriter writer = new FileWriter(fileToBeModified);
                writer.write(newFileContent);

                //closes resources
                reader.close();
                writer.close();
            }

            if (caseInsensitiveFlag == false && firstOnlyFlag == false && lastOnlyFlag == true) {
                //replaces the instance of the first string with the second
                //had to use regex to make my own replacelast
                String newFileContent = fileContent.replaceFirst("(?s)" + fromString + "(?!.*?" + fromString + ")", changeToString);

                //locates the file I would like to edit and writes to it
                FileWriter writer = new FileWriter(fileToBeModified);
                writer.write(newFileContent);

                //closes resources
                reader.close();
                writer.close();
            }

            if (caseInsensitiveFlag == false && firstOnlyFlag == true && lastOnlyFlag == false) {

                //replaces the instance of the first string with the second
                String newFileContent = fileContent.replaceFirst(fromString, changeToString);

                //locates the file I would like to edit and writes to it
                FileWriter writer = new FileWriter(fileToBeModified);
                writer.write(newFileContent);

                //closes resources
                reader.close();
                writer.close();
            }

            if (caseInsensitiveFlag == false && firstOnlyFlag == true && lastOnlyFlag == true) {

                //replaces the instance of the first string with the second
                String newFileContent = fileContent.replaceFirst(fromString, changeToString);
                //had to use regex to make my own replacelast
                newFileContent = fileContent.replaceFirst("(?s)" + fromString + "(?!.*?" + fromString + ")", changeToString);


                //locates the file I would like to edit and writes to it
                FileWriter writer = new FileWriter(fileToBeModified);
                writer.write(newFileContent);

                //closes resources
                reader.close();
                writer.close();

            }
            //possible issues
            if (caseInsensitiveFlag == true && firstOnlyFlag == false && lastOnlyFlag == false) {
                String fileContent2 = caseInsensitive(fileContent);

                //replaces the instance of the first string with the second
                String newFileContent = fileContent2.replaceAll(fromString, changeToString);

                //locates the file I would like to edit and writes to it
                FileWriter writer = new FileWriter(fileToBeModified);
                writer.write(newFileContent);

                //closes resources
                reader.close();
                writer.close();
            }
            //possibleissues
            if (caseInsensitiveFlag == true && firstOnlyFlag == false && lastOnlyFlag == true) {
                String fileContent2 = caseInsensitive(fileContent);

                //replaces the instance of the first string with the second
                String newFileContent = fileContent2.replaceFirst(fromString, changeToString);

                //locates the file I would like to edit and writes to it
                FileWriter writer = new FileWriter(fileToBeModified);
                writer.write(newFileContent);

                //closes resources
                reader.close();
                writer.close();
            }
            //possible issues
            if (caseInsensitiveFlag == true && firstOnlyFlag == true && lastOnlyFlag == false) {
                String fileContent2 = caseInsensitive(fileContent);

                //replaces the instance of the first string with the second
                String newFileContent = fileContent.replaceFirst("(?s)" + fromString + "(?!.*?" + fromString + ")", changeToString);

                //locates the file I would like to edit and writes to it
                FileWriter writer = new FileWriter(fileToBeModified);
                writer.write(newFileContent);

                //closes resources
                reader.close();
                writer.close();
            }
            //possible issues
            if (caseInsensitiveFlag == true && firstOnlyFlag == true && lastOnlyFlag == true) {
                String fileContent2 = caseInsensitive(fileContent);

                //replaces the instance of the first string with the second
                String newFileContent = fileContent2.replaceFirst(fromString, changeToString);
                newFileContent = fileContent.replaceFirst("(?s)" + fromString + "(?!.*?" + fromString + ")", changeToString);

                //locates the file I would like to edit and writes to it
                FileWriter writer = new FileWriter(fileToBeModified);
                writer.write(newFileContent);

                //closes resources
                reader.close();
                writer.close();
            }
        }
    }


    public void backupFile() {

        for (int i = 0; i < filenames.size(); i++) {
            Path source = Paths.get(filenames.get(i));
            Path target = Paths.get(filenames.get(i) + ".bck");
            try {
                Files.copy(source, target);
            } catch (IOException e1) {
                System.out.println("Error backup " + filenames.get(i) + ".bck Already exists");
            }
        }
    }

    public String caseInsensitive(String fileContent) {
        String[] temp = fileContent.split(" ");
        String output = "";
        for (int j = 0; j < temp.length; j++) {
            String temp2 = temp[j];
            if (temp2.equalsIgnoreCase(fromString)) {
                temp2 = temp2.toLowerCase();
            }
            output += temp2 + " ";
        }
        return output;
    }
}

