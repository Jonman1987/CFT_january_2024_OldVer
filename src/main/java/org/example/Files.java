package org.example;
import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Files {
    public static boolean isStop(ListItem[] list) {
        for (ListItem listItem : list) {
            if (!listItem.getFileEnd()) {
                return false;
            }
        }

        return true;
    }

    public static void fileWriter(int line, String outputFilename) {
        try {
            FileWriter writer = new FileWriter(outputFilename, true);

            writer.write(line + "\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void fileWriter(double line, String outputFilename) {
        try {
            FileWriter writer = new FileWriter(outputFilename, true);

            writer.write(line + "\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void fileWriter(String line, String outputFilename) {
        try {
            FileWriter writer = new FileWriter(outputFilename, true);

            writer.write(line + "\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(new Locale("en", "US")); // Use for dot
        String[] fileNames = new String[]{"input.txt", "input1.txt", "input2.txt"};
        ListItem[] conditions = new ListItem[fileNames.length];

        for (int i = 0; i < fileNames.length; i++) {
            conditions[i] = new ListItem(fileNames[i], false);
        }

        Scanner[] sources = new Scanner[fileNames.length];

        for (int i = 0; i < fileNames.length; i++) {
            try {
                sources[i] = new Scanner(new FileInputStream(fileNames[i]));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        String stringVariable;
        int intVariable;
        double doubleVariable;

        while (!isStop(conditions)) {
            try {
                for (int i = 0; i < fileNames.length; i++) {
                    if (sources[i].hasNextInt()) {
                        intVariable = sources[i].nextInt();
                        fileWriter(intVariable, "int.txt");
                    } else if ((sources[i].hasNextDouble())) {
                        doubleVariable = sources[i].nextDouble();
                        fileWriter(doubleVariable, "doub.txt");
                    } else if (sources[i].hasNextLine()) {
                        stringVariable = sources[i].nextLine();

                        if (stringVariable != null) {
                            if (!stringVariable.isEmpty()) {
                                fileWriter(stringVariable, "str.txt");
                            }
                        }
                    } else {
                        conditions[i].setFileEnd(true);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

