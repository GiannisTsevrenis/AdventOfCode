package Day5;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class Day5Part1 {
    private static HashMap<String, HashSet<String>> map = new HashMap<>();

    public static void main(String[] args) {
        String filePath = "./aoc2024/Day5/Input.txt";
        int totalMiddleItems = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null && !(line.isEmpty())) { // read file until Empty Line, insert all rules to a map
                String[] rules = line.split("\\|");
                if (map.containsKey(rules[1])) {
                    map.get(rules[1]).add(rules[0]);
                } else {
                    HashSet<String> newSet = new HashSet<>();
                    newSet.add(rules[0]);
                    map.put(rules[1], newSet);
                }
            }
            String[] res;
            while ((line = br.readLine()) != null) { // check each page
                res = line.split(",");
                if (hasCorrectPageOrder(res)) {
                    totalMiddleItems += Integer.parseInt(res[res.length / 2]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(totalMiddleItems);
    }

    private static boolean hasCorrectPageOrder(String[] str) {
        boolean result = true;
        for (int i = 0; i < str.length - 1; i++) {
            String current = str[i];
            for (int j = i + 1; j < str.length; j++) {
                if (map.get(current).contains(str[j])) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}
