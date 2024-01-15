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

    public static int elementsCount;

    public static void shortStat(String[] filename) {
        for (String string : filename) {
            elementsCount = 0;
            try (Scanner scanner = new Scanner(new FileInputStream(string))) {
                if (scanner.hasNextInt()) {
                    while (scanner.hasNext()) {
                        scanner.nextInt();
                        elementsCount++;
                    }
                } else if (scanner.hasNextDouble()) {
                    while (scanner.hasNext()) {
                        scanner.nextDouble();
                        elementsCount++;
                    }
                } else if (scanner.hasNextLine()) {
                    while (scanner.hasNext()) {
                        scanner.nextLine();
                        elementsCount++;
                    }
                } else {
                    break;
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Количество записанных элементов в файле " + string + " равно: " + elementsCount);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Locale.setDefault(new Locale("en", "US"));
        openFile("src/input.txt");
        String[] strings = new String[]{"example.txt", "example1.txt", "example2.txt"};
        shortStat(strings);
    }
}