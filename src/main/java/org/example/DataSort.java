package org.example;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class DataSort {
    public static void openFile(String filename) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream(filename))) {
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    try {
                        FileWriter writer = new FileWriter("example.txt", true);
                        int intNumber = scanner.nextInt();
                        writer.write(Integer.toString(intNumber));
                        writer.write("\n");
                        writer.close();
                    } catch (IOException e) {
                        System.out.println("Ошибка при записи в файл");
                    }
                } else if (scanner.hasNextDouble()) {
                    try {
                        FileWriter writer = new FileWriter("example1.txt", true);
                        double floatNumber = scanner.nextDouble();
                        writer.write(Double.toString(floatNumber));
                        writer.write("\n");
                        writer.close();
                    } catch (IOException e) {
                        System.out.println("Ошибка при записи в файл");
                    }
                } else if (scanner.hasNextLine()) {
                    try {
                        FileWriter writer = new FileWriter("example2.txt", true);
                        String line = scanner.nextLine();
                        writer.write(line);
                        writer.write("\r");
                        writer.close();
                    } catch (IOException e) {
                        System.out.println("Ошибка при записи в файл");
                    }
                } else {
                    break;
                }
            }
        }
    }

    public static void stat(String[] filename, String statType) {
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

            boolean intFile = false;
            boolean doubleFile = false;

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
                    intFile = true;
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
                    doubleFile = true;
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

            if (statType.equals("-s")) {
                System.out.println("Количество записанных элементов в файле " + string + " равно: " + elementsCount + ".\n");
            } else if (statType.equals("-f") && intFile) {
                System.out.println("Количество записанных элементов в файле " + string + " равно: " + elementsCount + ".");
                System.out.println("Сумма записанных элементов в файле " + string + " равна: " + intSum + ".");
                System.out.println("Среднее значение записанных элементов в файле " + string + " равна: " + average + ".");
                System.out.println("Минимальное значение записанных элементов в файле " + string + " равна: " + intMin + ".");
                System.out.println("Максимальное значение записанных элементов в файле " + string + " равна: " + intMax + ".\n");
            } else if (statType.equals("-f") && doubleFile) {
                System.out.println("Количество записанных элементов в файле " + string + " равно: " + elementsCount + ".");
                System.out.println("Сумма записанных элементов в файле " + string + " равна: " + doubleSum + ".");
                System.out.println("Среднее значение записанных элементов в файле " + string + " равна: " + average + ".");
                System.out.println("Минимальное значение записанных элементов в файле " + string + " равна: " + doubleMin + ".");
                System.out.println("Максимальное значение записанных элементов в файле " + string + " равна: " + doubleMax + ".\n");
            } else if (statType.equals("-f")) {
                System.out.println("Количество записанных строк в файле " + string + " равно: " + elementsCount + ".");
                System.out.println("Максимальная строка в файле " + string + " содержит: " + longString + "символов.");
                System.out.println("Минимальная строка в файле " + string + " содержит: " + shortString + "символов.\n");
            } else {
                System.out.println("Ошибка статистики в файле " + string + ".\n");
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Locale.setDefault(new Locale("en", "US")); // Use for dot
        openFile("src/input.txt");
        String[] strings = new String[]{"example.txt", "example1.txt", "example2.txt"};
        stat(strings, "-f");
        stat(strings, "-s");
    }
}