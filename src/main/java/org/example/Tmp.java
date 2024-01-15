package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tmp {
    public static int getOccurrencesCount(String fileName, String searchString) throws FileNotFoundException {
        int occurrencesCount = 0;
        searchString = searchString.toLowerCase();

        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while (scanner.hasNextLine()) {
                int startIndex = 0;
                String currentLine = scanner.nextLine().toLowerCase();

                while (true) {
                    int index = currentLine.indexOf(searchString, startIndex);
                    if (index == -1) {
                        break;
                    }

                    startIndex = index + searchString.length();
                    occurrencesCount++;
                }
            }
        }
        return occurrencesCount;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Количество вхождений равно: " + getOccurrencesCount("src/input.txt", "Привет"));
    }
}

