package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Files {

    public static boolean isStop(ListItem[] list){
        for (ListItem listItem : list) {
            if (!listItem.getFileEnd()) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String[] fileNames = new String[]{"input.txt", "input1.txt", "input2.txt"};
        ListItem[] conditions = new ListItem[fileNames.length];

        for(int i = 0; i < fileNames.length; i++){
            conditions[i] = new ListItem(fileNames[i], false);
        }

        BufferedReader[] sources = new BufferedReader[fileNames.length];
        for(int i = 0; i < fileNames.length; i++){
            try {
                sources[i] = new BufferedReader(new FileReader(fileNames[i]));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        String variable;
        while (!isStop(conditions)){
            try {
                for (int i = 0; i < fileNames.length; i++) {
                    variable = sources[i].readLine();
                    if(variable != null){
                        System.out.println(variable);
                    }else {
                        conditions[i].setFileEnd(true);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

