package Day1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day1Part1 {
    public static void main(String[] args) {

        String filePath = "./aoc2024/Day1/input1.txt";
        String[] numbers;
        int result = 0;
        ArrayList<Integer> firstColumn = new ArrayList<>();
        ArrayList<Integer> secondColumn = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                numbers = line.split("   ");
                firstColumn.add(Integer.parseInt(numbers[0]));
                secondColumn.add(Integer.parseInt(numbers[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(firstColumn);
        Collections.sort(secondColumn);
        for (int i = 0; i < firstColumn.size(); i++) {
            result += Math.abs(firstColumn.get(i) - secondColumn.get(i));
        }
        System.out.println(result);
    }
}
