package task_579;

import java.io.*;

import java.util.ArrayList;
import java.util.List;


public class Solution {
    public static void main(String[] args) throws IOException {
        String[] arrInput = readerInput();
        List<Integer> result = checkerResult(arrInput);
        writeResult(result);

    }

    private static List<Integer> checkerResult(String[] arrInput) {
        List<Integer> result, indexesOverZero = new ArrayList<>(), indexesLessZero = new ArrayList<>();
        int sumOverZero = 0, sumLessZero = 0, countZero = 0;
        for (int i = 0; i < arrInput.length; i++) {
            int x = Integer.parseInt(arrInput[i]);
            if (x > 0) {
                sumOverZero += x;
                indexesOverZero.add(i + 1);
            }
            if (x < 0) {
                sumLessZero += x;
                indexesLessZero.add(i + 1);
            }
            if (x == 0) {
                countZero++;
            }
        }
        if (countZero == arrInput.length) {
            result = new ArrayList<>();
        } else {
            if (Math.abs(sumOverZero) > Math.abs(sumLessZero)) {
                result = indexesOverZero;

            } else {
                result = indexesLessZero;
            }
        }
        return result;
    }

    private static String[] readerInput() throws IOException {
        String[] input;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/task_670/input.txt"))) {
            input = reader.lines().toArray()[1].toString().split(" ");
            reader.close();
        }
        return input;
    }

    private static void writeResult(List<Integer> indexes) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/task_579/output.txt"))) {
            if (indexes.size() == 0) {
                writer.write("0");
            } else {
                writer.write(indexes.size() + "\n");
                StringBuilder result = new StringBuilder(" ");
                for (Integer integer : indexes) {
                    result.append(integer.toString()).append(" ");
                }
                writer.write(String.valueOf(result).trim());
            }
            writer.close();
        }
    }
}
