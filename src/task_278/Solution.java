package task_278;

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        String[] input = readerInput();
        boolean check = checkerDNA(input);
        writeResult(check);
    }

    private static boolean checkerDNA(String[] input) {
       String s = input[0];
       String t= input[1];
       if (s.equals(""))return true;
       if (t==null)return false;
       int count=0;
       int index=0;
        for (int i = 0; i < t.length(); i++) {
            if (index<s.length()&&s.charAt(index)==t.charAt(i)){
                count++;
                index++;
            }
        }
        return count==s.length();
    }

    private static String[] readerInput() throws IOException {
        String[] strings = new String[2];
        try (BufferedReader reader = new BufferedReader(new FileReader("src/task_278/input.txt"))) {
            String s;
            int count = 0;
            while ((s = reader.readLine()) != null) {
                strings[count] = s;
                count++;
            }
            reader.close();
        }
        return strings;
    }

    private static void writeResult(boolean check) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/task_278/output.txt"))) {
            if (check) {
                writer.write("YES");
            } else writer.write("NO");
            writer.close();
        }
    }
}
