package task_670;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) throws IOException {
        int inputNumber = readerInput();
        int result = findResult(inputNumber);
        writeResult(result);
    }

    private static int findResult(int inputNumber) {
        int count = 0, result = 0;
        while (count != inputNumber) {
            result++;
            count++;
            if (!checkerResult(result)) {
                count--;
            }
        }
        return result;
    }

    private static boolean checkerResult(int result) {
        int count = 0;
        Set<Integer> set = new HashSet<>();
        while (result / 10 > 0) {
            set.add(result % 10);
            result = result / 10;
            count++;
        }
        set.add(result);
        count++;
        return set.size() == count;
    }

    private static int readerInput() throws IOException {
        int input;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/task_670/input.txt"))) {
            input = Integer.parseInt(reader.readLine());
            reader.close();
        }
        return input;
    }

    private static void writeResult(int result) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/task_670/output.txt"))) {
            writer.write(String.valueOf(result));
            writer.close();
        }
    }
}