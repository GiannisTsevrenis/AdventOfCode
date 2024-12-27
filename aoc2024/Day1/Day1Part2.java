package Day1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Day1Part2 {
    public static void main(String[] args) {
        String filePath = "./aoc2024/Day1/input1.txt";
        HashMap<Integer,Integer> map = new HashMap<>();
        long similarity = 0 ;
        String[] numbers;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                numbers = line.split("   ");
                map.merge(Integer.parseInt(numbers[1]),1, Integer::sum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentNumber;
            while ((line = br.readLine()) != null) {
                numbers = line.split("   ");
                currentNumber = Integer.parseInt(numbers[0]);
                if (map.containsKey(currentNumber)){
                    similarity+= ((long) map.get(currentNumber) * currentNumber);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(similarity);
    }
}