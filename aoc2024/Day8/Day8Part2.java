package Day8;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

// solution 1150
public class Day8Part2 {
    private static int bounds;

    public static void main(String[] args) {
        String filePath = "./aoc2024/Day8/input.txt";
        char[][] matrix = buildMatrix(filePath);
        HashSet<Point> result = new HashSet<>();
        HashMap<Character, ArrayList<Point>> frequenciesMap = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                char c = matrix[i][j];
                if (Character.isDigit(c) | Character.isLetter(c)) {
                    Point current = new Point(i, j);
                    if (!(frequenciesMap.containsKey(c))) { // if frequency is new add it to map
                        ArrayList<Point> newFrequency = new ArrayList<>();
                        newFrequency.add(current);
                        frequenciesMap.put(c, newFrequency);
                    } else {
                        for (Point point : frequenciesMap.get(c)) { // for each already existing char
                            // for each pair [currect,existing]
                            result.addAll(findAllPointsInLine(current, point));
                        }
                        frequenciesMap.get(c).add(current);
                    }
                }
            }
        }
        System.out.println(result.size());
    }

    private static char[][] buildMatrix(String filePath) {
        int lineCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while (br.readLine() != null) {
                lineCount++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bounds = lineCount;
        char[][] matrix = new char[bounds][bounds];
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int row = 0;
            String line;
            while ((line = br.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    matrix[row][i] = line.charAt(i);
                }
                row++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return matrix;
    }

    private static ArrayList<Point> findAllPointsInLine(Point a, Point b) {
        ArrayList<Point> pointArrayList = new ArrayList<>(List.of(a, b));
        Point vectorAB = new Point((b.x - a.x), (b.y - a.y)); // displacement vectors AB
        Point nextPoint = new Point(b);
        nextPoint.setLocation(nextPoint.x + vectorAB.x, nextPoint.y + vectorAB.y);
        while(isWithinBounds(nextPoint)){
            pointArrayList.add(new Point(nextPoint));
            nextPoint.setLocation(nextPoint.x + vectorAB.x, nextPoint.y + vectorAB.y);
        }
        Point vectorBA = new Point((a.x - b.x), (a.y - b.y));// displacement vectors AB
        nextPoint = new Point(a);
        nextPoint.setLocation(nextPoint.x + vectorBA.x, nextPoint.y + vectorBA.y);
        while (isWithinBounds(nextPoint)) {
            pointArrayList.add(new Point(nextPoint));
            nextPoint.setLocation(nextPoint.x + vectorBA.x, nextPoint.y + vectorBA.y);
        }
        return pointArrayList;
    }

    private static boolean isWithinBounds(Point a) {
        return a.x >= 0 && a.x < bounds && a.y >= 0 && a.y < bounds;
    }
}
