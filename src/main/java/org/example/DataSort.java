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

    /*public static void shortStat(String[] filename) {
        for (String string : filename) {
            int elementsCount = 0;
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
    }*/

    public static void stat(String[] filename, String statType) {
        for (String string : filename) {
            int elementsCount = 0;
            double average = 0;
            int intSum = 0;
            int intMax = 0;
            int intMin = 0;

            double doubleAverage = 0;
            double doubleSum = 0;
            double doubleMax = 0;
            double doubleMin = 0;

            String shortString = "";
            String longString = "";

            boolean stringFile = false;
            boolean intFile = false;
            boolean doubleFile = false;

            try (Scanner scanner = new Scanner(new FileInputStream(string))) {
                if (scanner.hasNextInt()) {
                    int intNumber = scanner.nextInt();
                    intMax = intNumber;
                    intMin = intNumber;
                    elementsCount++;

                    while (scanner.hasNext()) {
                        intNumber = scanner.nextInt();
                        elementsCount++;
                        intSum += intNumber;
                        if(intMin > intNumber){
                            intMin = intNumber;
                        }

                        if(intMax < intNumber){
                            intMax = intNumber;
                        }
                    }

                    average = (double) intSum / elementsCount;
                    intFile = true;
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

            if(statType.equals("-s")){
                System.out.println("Количество записанных элементов в файле " + string + " равно: " + elementsCount + ".\n");
            }else if(statType.equals("-f") && intFile){
                System.out.println("Количество записанных элементов в файле " + string + " равно: " + elementsCount);
                System.out.println("Сумма записанных элементов в файле " + string + " равна: " + intSum);
                System.out.println("Среднее значение записанных элементов в файле " + string + " равна: " + average);
                System.out.println("Минимальное значение записанных элементов в файле " + string + " равна: " + intMin);
                System.out.println("Максимальное значение записанных элементов в файле " + string + " равна: " + intMax + ".\n");
            }else {
                System.out.println("Ошибка статистики");
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Locale.setDefault(new Locale("en", "US"));
        openFile("src/input.txt");
        String[] strings = new String[]{"example.txt", "example1.txt", "example2.txt"};
        stat(strings, "-f");
    }
}