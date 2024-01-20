package org.example;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class DataSort {
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

    public static void openFile(String inputFilename, String[] outputFilename, String path, boolean isInputOptionA, int fileNumber) {
        boolean isFirstActionInt = false;
        boolean isFirstActionDouble = false;
        boolean isFirstActionString = false;

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

                        if (!isFirstActionInt && fileNumber == 0) {
                            intMin = intNumber;
                            intMax = intNumber;
                            isFirstActionInt = true;
                        }

                        if (intMin > intNumber) {
                            intMin = intNumber;
                        }

                        if (intMax < intNumber) {
                            intMax = intNumber;
                        }

                        intSum += intNumber;
                        elementsCountInt++;
                        isCreateIntFile = true;

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
                        double doubleNumber = scanner.nextDouble();

                        if (!isFirstActionDouble && fileNumber == 0) {
                            doubleMin = doubleNumber;
                            doubleMax = doubleNumber;
                            isFirstActionDouble = true;
                        }

                        elementsCountDouble++;
                        doubleSum += doubleNumber;
                        isCreateDoubleFile = true;

                        if (doubleMin > doubleNumber) {
                            doubleMin = doubleNumber;
                        }

                        if (doubleMax < doubleNumber) {
                            doubleMax = doubleNumber;
                        }

                        writer.write(Double.toString(doubleNumber));
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
                            if (!isFirstActionString && fileNumber == 0) {
                                shortString = line.length();
                                longString = line.length();
                                isFirstActionString = true;
                            }

                            writer.write(line + "\n");
                            elementsCountString++;

                            if (shortString > line.length()) {
                                shortString = line.length();
                            }

                            if (longString < line.length()) {
                                longString = line.length();
                            }
                        }

                        isCreateStringFile = true;

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
        } catch (IOException ex) {
            System.out.println("Не удается открыть файл");
        }
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

        stat(outputFilenames, statisticCount);
    }
}