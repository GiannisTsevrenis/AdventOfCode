package Day7;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7Part1 {
    public static void main(String[] args) {
        String filePath = "./aoc2024/Day7/input.txt";
        BigInteger total = BigInteger.valueOf(0);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                ArrayList<BigInteger> list = parseExpression(line);
                if (validateExpression(list)) {
                    total = total.add(list.get(0));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Total correct pages amount = " + total);
    }

    public static boolean validateExpression(ArrayList<BigInteger> intList) {
        ArrayList<BigInteger> temp = new ArrayList<>(intList);
        BigInteger goal = temp.remove(0);
        ArrayList<String> permutations = permute(new char[]{'+', '*'}, temp.size() - 1);
        for (String permutation : permutations) {
            BigInteger number = new BigInteger(temp.get(0).toString());
            int i = 1;
            for (char c : permutation.toCharArray()) {
                if (c == '+') {
                    number = number.add(temp.get(i));
                } else {
                    number = number.multiply(temp.get(i));
                }
                i++;
            }
            if (number.equals(goal)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> permute(char[] items, int spots) {
        ArrayList<String> permutations = new ArrayList<>();
        int totalPermutations = (int) Math.pow(items.length, spots);
        for (int i = 0; i < totalPermutations; i++) {
            StringBuilder sb = new StringBuilder();
            int temp = i;
            for (int j = 0; j < spots; j++) {
                sb.append(items[temp % items.length]);
                temp /= items.length;
            }
            permutations.add(String.valueOf(sb));
        }
        return permutations;
    }

    public static ArrayList<BigInteger> parseExpression(String str) {
        ArrayList<BigInteger> arrayList = new ArrayList<>();
        Pattern pattern = Pattern.compile("(\\d+)");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            arrayList.add(new BigInteger(matcher.group()));
        }
        return arrayList;
    }
}
