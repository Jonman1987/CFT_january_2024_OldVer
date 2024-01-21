package org.example;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Files {
    public static int elementsCountInt = 0;
    public static int elementsCountDouble = 0;
    public static int elementsCountString = 0;
    public static double averageInt = 0;
    public static double averageDouble = 0;
    public static int intSum = 0;
    public static int intMax = 0;
    public static int intMin = 0;
    public static double doubleSum = 0;
    public static double doubleMax = 0;
    public static double doubleMin = 0;
    public static int shortString = 0;
    public static int longString = 0;
    public static boolean isCreateIntFile = false;
    public static boolean isCreateDoubleFile = false;
    public static boolean isCreateStringFile = false;

    public static boolean isStop(ListItem[] list) {
        for (ListItem listItem : list) {
            if (!listItem.getFileEnd()) {
                return false;
            }
        }

        return true;
    }

    public static void fileWriter(int line, String outputFilename, String path, boolean isInputOptionA, int fileNumber, int iterationNumber) {
        boolean isFirstActionInt = false;
        boolean isFirstActionDouble = false;
        boolean isFirstActionString = false;

            File file = new File(outputFilename);

            if (file.exists() && !isInputOptionA && fileNumber == 0 && iterationNumber == 0) {
                System.out.println("Файл найден");
                boolean delete = file.delete();

                if (delete) {
                    System.out.println("Файл удален");
                }
        }

        File mkdirs = new File(path);
        boolean mkdirs1 = mkdirs.mkdirs();

        try {
            FileWriter writer = new FileWriter(outputFilename, true);

            writer.write(line + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл");
        }
    }

    public static void fileWriter(double line, String outputFilename, String path, boolean isInputOptionA, int fileNumber, int iterationNumber) {
        boolean isFirstActionInt = false;
        boolean isFirstActionDouble = false;
        boolean isFirstActionString = false;

        File file = new File(outputFilename);

        if (file.exists() && !isInputOptionA && fileNumber == 0 && iterationNumber == 0) {
            System.out.println("Файл найден");
            boolean delete = file.delete();

            if (delete) {
                System.out.println("Файл удален");
            }
        }

        File mkdirs = new File(path);
        boolean mkdirs1 = mkdirs.mkdirs();

        try {
            FileWriter writer = new FileWriter(outputFilename, true);

            writer.write(line + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл");
        }
    }

    public static void fileWriter(String line, String outputFilename, String path, boolean isInputOptionA, int fileNumber, int iterationNumber) {
        boolean isFirstActionInt = false;
        boolean isFirstActionDouble = false;
        boolean isFirstActionString = false;

        File file = new File(outputFilename);

        if (file.exists() && !isInputOptionA && fileNumber == 0 && iterationNumber == 0) {
            System.out.println("Файл найден");
            boolean delete = file.delete();

            if (delete) {
                System.out.println("Файл удален");
            }
        }

        File mkdirs = new File(path);
        boolean mkdirs1 = mkdirs.mkdirs();

        try {
            FileWriter writer = new FileWriter(outputFilename, true);

            writer.write(line + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл");
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(new Locale("en", "US")); // Use for dot

        String[] outputFilenames = new String[]{"integers.txt", "floats.txt", "strings.txt"};


        String path = "";
        int statisticCount = 0;
        boolean isInputOptionA = false;
        int inputFileCount = 0;

        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);

            if (args[i].equals("-o") || args[i].equals("-O")) {
                System.out.println("Содержит -o");
                path = args[i + 1]; // Проверить директорию
            } else if (args[i].equals("-p") || args[i].equals("-P")) {
                System.out.println("Содержит -p");

                for (int j = 0; j < outputFilenames.length; j++) {
                    outputFilenames[j] = args[i + 1] + outputFilenames[j];
                }
            } else if (args[i].equals("-f") || args[i].equals("-F")) {
                System.out.println("Содержит -f");
                statisticCount = 1;
            } else if (args[i].equals("-s") || args[i].equals("-S")) {
                System.out.println("Содержит -s");
                statisticCount = 2;
            } else if (args[i].equals("-a") || args[i].equals("-A")) {
                System.out.println("Содержит -a");
                isInputOptionA = true;
            } else if (args[i].toLowerCase().contains(".txt")) {
                inputFileCount++;
            }
        }

        String[] inputFileNames = new String[inputFileCount];

        int j = 0;

        for (String arg : args) { //Добавить исключение для названия .txt
            if (arg.toLowerCase().contains(".txt")) {
                inputFileNames[j] = arg;
                j++;
            }
        }

        ListItem[] conditions = new ListItem[inputFileNames.length];

        for (int i = 0; i < inputFileNames.length; i++) {
            conditions[i] = new ListItem(inputFileNames[i], false);
        }

        Scanner[] sources = new Scanner[inputFileNames.length];

        for (int i = 0; i < inputFileNames.length; i++) {
            try {
                sources[i] = new Scanner(new FileInputStream(inputFileNames[i]));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        String stringVariable;
        int intVariable;
        double doubleVariable;

        int fileNumber = 0;
        int iterationNumber = 0;

        while (!isStop(conditions)) {
            try {
                for (int i = 0; i < inputFileNames.length; i++) {
                    if (sources[i].hasNextInt()) {
                        intVariable = sources[i].nextInt();
                        fileWriter(intVariable, outputFilenames[0], path, isInputOptionA, fileNumber, iterationNumber);
                    } else if ((sources[i].hasNextDouble())) {
                        doubleVariable = sources[i].nextDouble();
                        fileWriter(doubleVariable, outputFilenames[1], path, isInputOptionA, fileNumber, iterationNumber);
                    } else if (sources[i].hasNextLine()) {
                        stringVariable = sources[i].nextLine();

                        if (stringVariable != null) {
                            if (!stringVariable.isEmpty()) {
                                fileWriter(stringVariable, outputFilenames[2], path, isInputOptionA, fileNumber, iterationNumber);
                            }
                        }
                    } else {
                        conditions[i].setFileEnd(true);
                    }

                    fileNumber++;
                }

                iterationNumber++;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

