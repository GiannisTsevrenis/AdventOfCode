package Day5;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Day5Part2 {
    private static HashMap<String, HashSet<String>> map = new HashMap<>(); // will contain rules in opposite order(to check from left to right while looping over pages)

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
                if (!hasCorrectPageOrder(res)) {
                    reOrderPage(res);
                    if (hasCorrectPageOrder(res)) {
                        int middle = Integer.parseInt(res[res.length / 2]);
                        totalMiddleItems += middle;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(totalMiddleItems);
    }

    private static void reOrderPage(String[] str) {
        int len = str.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < len - 1; i++) {
                String current = str[i];
                for (int j = i + 1; j < len; j++) {
                    if (map.containsKey(current)) {
                        if (map.get(current).contains(str[j])) {
                            String temp = str[i];
                            str[i] = str[j];
                            str[j] = temp;
                            swapped = true;
                        }
                    }
                }
                if (swapped) {
                    break;
                }
            }
        } while (swapped);
    }

    private static boolean hasCorrectPageOrder(String[] str) {
        boolean result = true;
        for (int i = 0; i < str.length - 1; i++) {
            String current = str[i];
            for (int j = i + 1; j < str.length; j++) {
                if (map.containsKey(current)) {
                    if (map.get(current).contains(str[j])) {
                        return false;
                    }
                }
            }
        }
        return result;
    }
}
