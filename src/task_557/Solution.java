package task_557;

import java.io.*;


public class Solution {
    private static int countMatrix;
    private static int sizeMatrix;
    private static int line;
    private static int column;
    private static int p;

    public static void main(String[] args) throws IOException {
        String[] input = readerInput();
        int[][] matrix;
        int result;
        if (countMatrix > 1) {
            matrix = getMultiMatrix(input, Runtime.getRuntime().availableProcessors());
            result = matrix[line - 1][column - 1];
        } else result = Integer.parseInt(input[line - 1].split(" ")[column - 1]);
        result = checkoutResult(result);
        writeResult(result);
    }

    private static int checkoutResult(int result) {
        if (result >= Math.abs(p)) result = result % p;
        return result;
    }


    private static int[][] getMultiMatrix(String[] input, int threadCount) {
        assert threadCount > 0;

        final int[][] result = new int[sizeMatrix][sizeMatrix];

        final int cellsForThread = (sizeMatrix * sizeMatrix) / threadCount;
        int firstIndex = 0;
        final MultiThread[] multiplierThreads = new MultiThread[threadCount];

        for (int threadIndex = threadCount - 1; threadIndex >= 0; --threadIndex) {
            int lastIndex = firstIndex + cellsForThread;
            if (threadIndex == 0) {
                lastIndex = sizeMatrix * sizeMatrix;
            }
            multiplierThreads[threadIndex] = new MultiThread(input, result, firstIndex, lastIndex, sizeMatrix, countMatrix, p);
            multiplierThreads[threadIndex].start();
            firstIndex = lastIndex;
        }
        try {
            for (final MultiThread multiplierThread : multiplierThreads)
                multiplierThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String[] readerInput() throws IOException {
        String[] strings = new String[5];
        try (BufferedReader reader = new BufferedReader(new FileReader("src/task_557/input.txt"))) {
            String s;
            for (int i = 0; i < strings.length - 1; i++) {
                s = reader.readLine();
                if (s == null) break;
                String[] str = s.split(" ");
                strings[i] = str[0];
                strings[i + 1] = str[1];
                i++;
            }
            countMatrix = Integer.parseInt(strings[0]);
            sizeMatrix = Integer.parseInt(strings[1]);
            line = Integer.parseInt(strings[2]);
            column = Integer.parseInt(strings[3]);
            p = Integer.parseInt(reader.readLine());
            strings = new String[countMatrix * sizeMatrix];
            int count = 0;
            while ((s = reader.readLine()) != null) {
                if (!s.equals("")) {
                    strings[count] = s;
                    count++;
                }
            }
            reader.close();
        }
        return strings;
    }

    private static void writeResult(int result) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/task_557/output.txt"))) {
            writer.write(String.valueOf(result));
            writer.close();
        }
    }
}