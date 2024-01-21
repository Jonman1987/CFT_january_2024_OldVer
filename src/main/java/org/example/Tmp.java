package org.example;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tmp {
    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader source1 = null;
        BufferedReader source2 = null;
        BufferedReader source3 = null;
        boolean isSource0HasEnd = false;
        boolean isSource1HasEnd = false;
        boolean isSource2HasEnd = false;
        boolean[] endFile = new boolean[]{isSource0HasEnd, isSource1HasEnd, isSource2HasEnd};
        String partOne = "";
        String partTwo = "";
        String partThree = "";
        String[] varible = new String[] {partOne, partTwo, partThree};
        BufferedReader[] sources = new BufferedReader[]{source1, source2, source3};
        String[] strings = new String[] {"input1.txt", "input2.txt"};
        for (int i = 0; i < strings.length; i++) {
            sources[i] = new BufferedReader(new FileReader(strings[i]));
        }

        /*for (int i = strings.length; i < endFile.length; i++) {
            endFile[i] = true;
        }*/

        int k = 0;
        while (!isSource0HasEnd && !isSource1HasEnd && !isSource2HasEnd) {

            try {
                for (int i = 0; i < strings.length; i++) {
                    varible[i] = sources[i].readLine();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            for (int i = 0; i < strings.length; i++) {
                if(varible[i] == null){
                    endFile[i] = true;
                }
            }

            for (int i = strings.length; i < endFile.length; i++) {
                endFile[i] = true;
            }

            System.out.println(partOne + "\t" + partTwo + "\t" + partThree);
        }
    }
}

