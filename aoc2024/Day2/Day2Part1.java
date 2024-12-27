package Day2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Day2Part1 {
    public static void main(String[] args) {
        String filePath = "./aoc2024/Day2/input1.txt";
        int sum = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                int[] array = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                if (isDecreasing(array) || isIncreasing(array)) {
                    sum++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sum);
    }

    public static Boolean isDecreasing(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if ((nums[i] < nums[i - 1]) && ((nums[i - 1] - nums[i])) <= 3) {
            } else {
                return false;
            }
        }
        return true;
    }

    public static Boolean isIncreasing(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if ((nums[i] > nums[i - 1]) && ((nums[i] - nums[i - 1])) <= 3) {
            } else {
                return false;
            }
        }
        return true;
    }
}
