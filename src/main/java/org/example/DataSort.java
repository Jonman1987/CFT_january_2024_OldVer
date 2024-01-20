package org.example;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class DataSort {
    public static void openFile(String inputFilename, String[] outputFilename, String path, boolean isInputOptionA, int fileNumber) {
        for (String string : outputFilename) {
            File file = new File(string);

            if (file.exists() && !isInputOptionA && fileNumber == 0) {
                System.out.println("Файл найден");
                boolean delete = file.delete();

                if (delete) {
                    System.out.println("Файл удален");
                }
            }
        }

        File mkdirs = new File(path);
        boolean mkdirs1 = mkdirs.mkdirs();

        try (Scanner scanner = new Scanner(new FileInputStream(inputFilename))) {
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    try {
                        FileWriter writer = new FileWriter(path + outputFilename[0], true);

                        int intNumber = scanner.nextInt();
                        writer.write(Integer.toString(intNumber));
                        writer.write("\n");
                        writer.close();
                    } catch (IOException e) {
                        System.out.println("Ошибка при записи в файл");
                        break;
                    }
                } else if (scanner.hasNextDouble()) {
                    try {
                        FileWriter writer = new FileWriter(path + outputFilename[1], true);
                        double floatNumber = scanner.nextDouble();

                        writer.write(Double.toString(floatNumber));
                        writer.write("\n");
                        writer.close();
                    } catch (IOException e) {
                        System.out.println("Ошибка при записи в файл");
                        break;
                    }
                } else if (scanner.hasNextLine()) {
                    try {
                        FileWriter writer = new FileWriter(path + outputFilename[2], true);
                        String line = scanner.nextLine();

                        if (!line.isEmpty()) {
                            writer.write(line + "\n");
                        }

                        writer.close();
                    } catch (IOException e) {
                        System.out.println("Ошибка при записи в файл");
                        break;
                    }
                } else {
                    System.out.println("Ошибка при считывании данных с файла");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Не удается открыть файл");
        }
    }

    public static void stat(String[] filename, String path, int statType) {
        for (int i = 0; i < filename.length; i++) {
            filename[i] = path + filename[i];
        }

        for (String string : filename) {
            int elementsCount = 0;
            double average = 0;
            int intSum = 0;
            int intMax = 0;
            int intMin = 0;

            double doubleSum = 0;
            double doubleMax = 0;
            double doubleMin = 0;

            int shortString = 0;
            int longString = 0;

            boolean isCreateIntFile = false;
            boolean isCreateDoubleFile = false;

            try (Scanner scanner = new Scanner(new FileInputStream(string))) {
                if (scanner.hasNextInt()) {
                    int intNumber = scanner.nextInt();
                    intMax = intNumber;
                    intMin = intNumber;
                    intSum += intNumber;
                    elementsCount++;

                    while (scanner.hasNext()) {
                        intNumber = scanner.nextInt();
                        elementsCount++;
                        intSum += intNumber;

                        if (intMin > intNumber) {
                            intMin = intNumber;
                        }

                        if (intMax < intNumber) {
                            intMax = intNumber;
                        }
                    }

                    average = (double) intSum / elementsCount;
                    isCreateIntFile = true;
                } else if (scanner.hasNextDouble()) {
                    double doubleNumber = scanner.nextDouble();
                    doubleMax = doubleNumber;
                    doubleMin = doubleNumber;
                    doubleSum += doubleNumber;
                    elementsCount++;

                    while (scanner.hasNext()) {
                        doubleNumber = scanner.nextDouble();
                        elementsCount++;
                        doubleSum += doubleNumber;

                        if (doubleMin > doubleNumber) {
                            doubleMin = doubleNumber;
                        }

                        if (doubleMax < doubleNumber) {
                            doubleMax = doubleNumber;
                        }
                    }

                    average = doubleSum / elementsCount;
                    isCreateDoubleFile = true;
                } else if (scanner.hasNextLine()) {
                    String stringWord = scanner.nextLine();
                    elementsCount++;
                    shortString = stringWord.length();
                    longString = stringWord.length();

                    while (scanner.hasNext()) {
                        stringWord = scanner.nextLine();
                        elementsCount++;

                        if (shortString > stringWord.length()) {
                            shortString = stringWord.length();
                        }

                        if (longString < stringWord.length()) {
                            longString = stringWord.length();
                        }
                    }
                } else {
                    break;
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            if (statType == 2) {
                System.out.println("Количество записанных элементов в файле " + string + " равно: " + elementsCount + ".\n");
            } else if (statType == 1 && isCreateIntFile) {
                System.out.println("Количество записанных элементов в файле " + string + " равно: " + elementsCount + ".");
                System.out.println("Сумма записанных элементов в файле " + string + " равна: " + intSum + ".");
                System.out.println("Среднее значение записанных элементов в файле " + string + " равна: " + average + ".");
                System.out.println("Минимальное значение записанных элементов в файле " + string + " равна: " + intMin + ".");
                System.out.println("Максимальное значение записанных элементов в файле " + string + " равна: " + intMax + ".\n");
            } else if (statType == 1 && isCreateDoubleFile) {
                System.out.println("Количество записанных элементов в файле " + string + " равно: " + elementsCount + ".");
                System.out.println("Сумма записанных элементов в файле " + string + " равна: " + doubleSum + ".");
                System.out.println("Среднее значение записанных элементов в файле " + string + " равна: " + average + ".");
                System.out.println("Минимальное значение записанных элементов в файле " + string + " равна: " + doubleMin + ".");
                System.out.println("Максимальное значение записанных элементов в файле " + string + " равна: " + doubleMax + ".\n");
            } else if (statType == 1) {
                System.out.println("Количество записанных строк в файле " + string + " равно: " + elementsCount + ".");
                System.out.println("Максимальная строка в файле " + string + " содержит: " + longString + " символов.");
                System.out.println("Минимальная строка в файле " + string + " содержит: " + shortString + " символов.\n");
            } else if (statType != 0) {
                System.out.println("Ошибка статистики в файле " + string + ".\n");
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
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
        int i = 0;

        for (String arg : args) { //Добавить исключение для названия .txt
            if (arg.toLowerCase().contains(".txt")) {
                inputFileNames[i] = arg;
                i++;
            }
        }

        i = 0;

        for (String inputFileName : inputFileNames) {
            openFile(inputFileName, outputFilenames, path, isInputOptionA, i);
            i++;
        }

        stat(outputFilenames, path, statisticCount);
    }
}