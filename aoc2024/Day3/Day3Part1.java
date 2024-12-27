package Day3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Part1 {
    public static void main(String[] args) {
        String filePath = "./aoc2024/Day3/PuzzleInput.txt";
        String regex = "(?<=mul\\()(\\d{1,3},\\d{1,3})(?=\\))";
        ArrayList<String> matches = matchRegexFromFile(filePath, regex);
        int result = 0;
        for (String match : matches) {
            String[] multiplication = match.split(",");
            result += Integer.parseInt(multiplication[0]) * Integer.parseInt(multiplication[1]);
        }
        System.out.println(matches.size());
        System.out.println(result);
    }

    private static ArrayList<String> matchRegexFromFile(String filePath, String regex) {
        ArrayList<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    result.add(matcher.group());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
