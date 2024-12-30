package Day9;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
// my solution: 6320029754031

public class Day9Part1 {
    public static void main(String[] args) {
        String filePath = "./aoc2024/Day9/input.txt";//
        ArrayList<Integer> arraylist = buildBlocksList(filePath);//
        int lastDigit = fillFreeSpaceBlocks(arraylist); // puts all digits at the start and returns index of last digit
        BigInteger res = BigInteger.valueOf(0);
        for (int j = 0; j <= lastDigit; j++) {
            res = res.add(BigInteger.valueOf((long) j * arraylist.get(j)));
        }
        System.out.println(res);
    }

    private static int fillFreeSpaceBlocks(ArrayList<Integer> arraylist) {
        int j = arraylist.size() - 1;
        int i = 0;
        while (i < j) {
            if (arraylist.get(i) == -1) { // swap variables and move counters
                arraylist.set(i, arraylist.get(j));
                arraylist.set(j, -1);
                do {
                    j--;
                } while (arraylist.get(j) == -1);
            }
            i++;
        }
        return i;
    }

    public static ArrayList<Integer> buildBlocksList(String filePath) {
        int id = 0;
        ArrayList<Integer> result = new ArrayList<>();
        boolean isFile = true;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    if (isFile) {
                        for (int i = 0; i < c - '0'; i++) {
                            result.add(id);
                        }
                        id++;
                    } else {
                        for (int i = 0; i < c - '0'; i++) {
                            result.add(-1);
                        }
                    }
                    isFile = !isFile;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
