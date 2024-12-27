package Day3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Part2 {
    public static void main(String[] args) {
        String filePath = "./aoc2024/Day3/PuzzleInput.txt";
        String regex = "(?<=mul\\()(\\d{1,3},\\d{1,3})(?=\\))|(do\\(\\))|don't\\(\\)"; //match mul(X,Y) | do() | don't()
        ArrayList<String> matches = matchRegexFromFile(filePath, regex);
        int result = 0;
        boolean flag = true;
        for (String match : matches) {
            if (match.equals("do()")) {
                flag = true;
            } else if (match.equals("don't()")) {
                flag = false;
            } else {
                if (flag) {
                    String[] multiplication = match.split(",");
                    result += Integer.parseInt(multiplication[0]) * Integer.parseInt(multiplication[1]);
                }
            }
        }
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
