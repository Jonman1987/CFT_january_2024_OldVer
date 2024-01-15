package org.example;

import java.io.*;
import java.util.Scanner;

public class DataSort {
    public static void openFile(String filename) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream(filename))) {
            while (scanner.hasNextLine()){
                if (scanner.hasNextInt()) {
                    try {
                        FileWriter writer = new FileWriter("example.txt", true);
                        int  a = scanner.nextInt();
                        writer.write(Integer.toString(a));
                        writer.close();
                    } catch (IOException e) {
                        System.out.println("Ошибка при записи в файл");
                    }
                }else{
                       break;
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        openFile("src/input.txt");
    }
}