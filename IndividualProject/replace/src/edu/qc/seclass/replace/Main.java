package edu.qc.seclass.replace;

import javafx.scene.web.HTMLEditorSkin;

public class Main {

    public static void main(String[] args) {
        // TODO: Empty skeleton method

        //String command = "replace -i RaNdom1 Hello -- file1.txt file2.txt file3.txt";

        String command = String.join(",", args);
        try {
            ReplaceUtility myUtility = new ReplaceUtility(command);

        } catch (Exception e){
            System.err.println("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- " + "<filename> [<filename>]*" );
        }
        command = null;
    }



}