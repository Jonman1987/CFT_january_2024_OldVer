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
    public static boolean isFirstActionInt = false;
    public static boolean isFirstActionDouble = false;
    public static boolean isFirstActionString = false;

    public static boolean isStop(ListItem[] list) {
        for (ListItem listItem : list) {
            if (!listItem.getFileEnd()) {
                return false;
            }
        }

        return true;
    }

    public static void fileWriter(int number, String outputFilename, String path) {
        try {
            FileWriter writer = new FileWriter(path + outputFilename, true);

            if (!isFirstActionInt) {
                intMin = number;
                intMax = number;
                isFirstActionInt = true;
            }

            if (intMin > number) {
                intMin = number;
            }

            if (intMax < number) {
                intMax = number;
            }

            intSum += number;
            elementsCountInt++;
            isCreateIntFile = true;

            writer.write(number + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл\n");
        }
    }

    public static void fileWriter(double number, String outputFilename, String path) {
        try {
            FileWriter writer = new FileWriter(path + outputFilename, true);

            if (!isFirstActionDouble) {
                doubleMin = number;
                doubleMax = number;
                isFirstActionDouble = true;
            }

            elementsCountDouble++;
            doubleSum += number;
            isCreateDoubleFile = true;

            if (doubleMin > number) {
                doubleMin = number;
            }

            if (doubleMax < number) {
                doubleMax = number;
            }

            writer.write(number + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл\n");
        }
    }

    public static void fileWriter(String line, String outputFilename, String path) {
        try {
            FileWriter writer = new FileWriter(path + outputFilename, true);

            if (!isFirstActionString) {
                shortString = line.length();
                longString = line.length();
                isFirstActionString = true;
            }

            elementsCountString++;
            isCreateStringFile = true;

            if (shortString > line.length()) {
                shortString = line.length();
            }

            if (longString < line.length()) {
                longString = line.length();
            }

            writer.write(line + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл\n");
        }
    }

    public static void checkDirectory(String[] outputFilename, String path, boolean isInputOptionA) {
        for (String s : outputFilename) {
            File file = new File(path + s);

            if (file.exists() && !isInputOptionA) {
                boolean delete = file.delete();
            }
        }

        File mkdirs = new File(path);
        mkdirs.mkdirs();
    }

    public static void stat(String[] filename, int statType) {
        averageInt = (double) intSum / elementsCountInt;
        averageDouble = doubleSum / elementsCountDouble;

        for (String string : filename) {
            if (statType == 2 && isCreateIntFile) {
                System.out.println("Количество записанных элементов в файле " + string + " равно: " + elementsCountInt + ".\n");
                isCreateIntFile = false;
            } else if (statType == 2 && isCreateDoubleFile) {
                System.out.println("Количество записанных элементов в файле " + string + " равно: " + elementsCountDouble + ".\n");
                isCreateDoubleFile = false;
            } else if (statType == 2 && isCreateStringFile) {
                System.out.println("Количество записанных элементов в файле " + string + " равно: " + elementsCountString + ".\n");
                isCreateStringFile = false;
            } else if (statType == 1 && isCreateIntFile) {
                System.out.println("Количество записанных элементов в файле " + string + " равно: " + elementsCountInt + ".");
                System.out.println("Сумма записанных элементов в файле " + string + " равна: " + intSum + ".");
                System.out.println("Среднее значение записанных элементов в файле " + string + " равна: " + averageInt + ".");
                System.out.println("Минимальное значение записанных элементов в файле " + string + " равна: " + intMin + ".");
                System.out.println("Максимальное значение записанных элементов в файле " + string + " равна: " + intMax + ".\n");
                isCreateIntFile = false;
            } else if (statType == 1 && isCreateDoubleFile) {
                System.out.println("Количество записанных элементов в файле " + string + " равно: " + elementsCountDouble + ".");
                System.out.println("Сумма записанных элементов в файле " + string + " равна: " + doubleSum + ".");
                System.out.println("Среднее значение записанных элементов в файле " + string + " равна: " + averageDouble + ".");
                System.out.println("Минимальное значение записанных элементов в файле " + string + " равна: " + doubleMin + ".");
                System.out.println("Максимальное значение записанных элементов в файле " + string + " равна: " + doubleMax + ".\n");
                isCreateDoubleFile = false;
            } else if (statType == 1 && isCreateStringFile) {
                System.out.println("Количество записанных строк в файле " + string + " равно: " + elementsCountString + ".");
                System.out.println("Максимальная строка в файле " + string + " содержит: " + longString + " символов.");
                System.out.println("Минимальная строка в файле " + string + " содержит: " + shortString + " символов.\n");
                isCreateStringFile = false;
            }
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(new Locale("en", "US")); // Use for dot

        String[] outputFilenames = new String[]{"integers.txt", "instruction.txt", "strings.txt"};

        String path = "";
        int statisticCount = 0;
        boolean isInputOptionA = false;
        int inputFileCount = 0;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-o") || args[i].equals("-O")) {
                try {
                    path = args[i + 1];

                    if (path.charAt(0) != '-') {
                        if (path.charAt(0) == '/') {
                            StringBuilder sb = new StringBuilder(path);
                            sb.deleteCharAt(0);
                            path = sb.toString();
                        }

                        if (path.charAt(path.length() - 1) != '/') {
                            path = path + '/';
                        }
                    } else {
                        path = "";
                        System.out.println("Внимание: Вы не указали путь после команды -o. Файлы сохранены в текущую папку\n");
                    }
                }catch (Exception e){
                    System.out.println("Ошибка: Отсутствуют аргументы параметра -o\n");
                }
            } else if (args[i].equals("-p") || args[i].equals("-P")) {
                try {
                    if (args[i + 1].charAt(0) == '-') {
                        System.out.println("Внимание: Вы не указали префикс названия файла после команды -p. Файлы сохранены с именем по умолчанию\n");
                    } else if (args[i + 1].toLowerCase().contains(".txt")) {
                        System.out.println("Внимание: Вы указали название файла вместо префикса. Файлы сохранены с именем по умолчанию\n");
                    } else if (args[i + 1].toLowerCase().contains(".") || args[i + 1].toLowerCase().contains("*") || args[i + 1].toLowerCase().contains("/")
                            || args[i + 1].toLowerCase().contains("?") || args[i + 1].toLowerCase().contains(":") || args[i + 1].toLowerCase().contains("|")
                            || args[i + 1].toLowerCase().contains("\"") || args[i + 1].toLowerCase().contains("<") || args[i + 1].toLowerCase().contains(">")) {
                        System.out.println("Внимание: Вы указали префикс названия файла с использованием спецсимвола. Файлы сохранены с именем по умолчанию\n");
                    } else {
                        for (int j = 0; j < outputFilenames.length; j++) {
                            outputFilenames[j] = args[i + 1] + outputFilenames[j];
                        }
                    }
                }catch (Exception e){
                    System.out.println("Ошибка: Отсутствуют аргументы параметра -p\n");
                }
            } else if (args[i].equals("-f") || args[i].equals("-F")) {
                statisticCount = 1;
            } else if (args[i].equals("-s") || args[i].equals("-S")) {
                statisticCount = 2;
            } else if (args[i].equals("-a") || args[i].equals("-A")) {
                isInputOptionA = true;
            } else if (args[i].toLowerCase().contains(".txt")) {
                inputFileCount++;
            }
        }

        String[] inputFileNames = new String[inputFileCount];

        int j = 0;

        for (String arg : args) {
            if (arg.toLowerCase().contains(".txt")) {
                inputFileNames[j] = arg;
                j++;
            }
        }

        if(inputFileNames.length == 0){
            System.out.println("Внимание: Вы не указали ни одного имени исходного файла в формате .txt\n");
        }

        ListItem[] conditions = new ListItem[inputFileNames.length];

        for (int i = 0; i < inputFileNames.length; i++) {
            conditions[i] = new ListItem(inputFileNames[i], false);
        }

        Scanner[] sources = new Scanner[inputFileNames.length];

        for (int i = 0; i < inputFileNames.length; i++) {
            try {
                sources[i] = new Scanner(new FileInputStream(inputFileNames[i]));
            } catch (Exception e) {
                System.out.println("Ошибка: Указанный вами исходный файл не найден\n");
            }
        }

        String stringVariable;
        int intVariable;
        double doubleVariable;

        if(inputFileNames.length != 0){
            checkDirectory(outputFilenames, path, isInputOptionA);
        }

        while (!isStop(conditions)) {
            try {
                for (int i = 0; i < inputFileNames.length; i++) {
                    if (sources[i].hasNextInt()) {
                        intVariable = sources[i].nextInt();
                        fileWriter(intVariable, outputFilenames[0], path);
                    } else if ((sources[i].hasNextDouble())) {
                        doubleVariable = sources[i].nextDouble();
                        fileWriter(doubleVariable, outputFilenames[1], path);
                    } else if (sources[i].hasNextLine()) {
                        stringVariable = sources[i].nextLine();

                        if (stringVariable != null) {
                            if (!stringVariable.isEmpty()) {
                                fileWriter(stringVariable, outputFilenames[2], path);
                            }
                        }
                    } else {
                        conditions[i].setFileEnd(true);
                    }
                }
            } catch (Exception e) {
                System.out.println("Ошибка чтения строки из файла\n");
                break;
            }
        }

        stat(outputFilenames, statisticCount);
    }
}

