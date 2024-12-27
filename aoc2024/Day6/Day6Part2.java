package Day6;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Day6Part2 {
    private static int x;
    private static int y;
    private static char[][] matrix;
    private static final int dimensions = 130;

    public static void main(String[] args) {
        // 130 x 130 matrix | unique chars : [#, ., ^]
        String filePath = "./aoc2024/Day6/Input.txt";
        buildMatrix(filePath);
        HashSet<Point> uniqueSteps = new HashSet<>(uniqueSteps()); // run one simulation and get the guard's path
        int countObstacles = 0;
        for (Point uniqueStep : uniqueSteps) { // run a simulation for each of the guards unique spots as an obstacle
            matrix[(int) uniqueStep.getX()][(int) uniqueStep.getY()] = '#';
            if (isGuardPatrolSimulationLoop()) {
                countObstacles++;
            }
            matrix[(int) uniqueStep.getX()][(int) uniqueStep.getY()] = '.';
        }
        System.out.println(countObstacles);
    }

    private static HashSet<Point> uniqueSteps() {
        int curX = x;
        int curY = y;
        Point currentLocation = new Point(x, y);
        HashSet<Point> stepCounter = new HashSet<>(); // to keep track of unique steps
        stepCounter.add(currentLocation);
        int towards = 0; // pointer = (pointer + 1) % size_of_array; (circular traversal)
        int[][] directions = {
                {-1, 0},  // Up
                {0, 1},   // Right
                {1, 0},   // Down
                {0, -1},  // Left
        };
        int newX, newY;
        do {
            newX = curX + directions[towards][0];
            newY = curY + directions[towards][1];
            if (!isWithinBounds(newX, newY)) {
                return stepCounter;
            }
            if (matrix[newX][newY] == '#') {
                towards = (towards + 1) % directions.length;
            } else {
                curX = newX;
                curY = newY;
                stepCounter.add(new Point(curX, curY));
            }
        } while (true);
    }

    private static boolean isGuardPatrolSimulationLoop() {
        int curX = x;
        int curY = y;
        HashSet<Point3D> stepCounter = new HashSet<>(); // to keep track of unique steps
        int towards = 0; // pointer = (pointer + 1) % size_of_array; (circular traversal)
        stepCounter.add(new Point3D(x, y, towards));
        int[][] directions = {
                {-1, 0},  // Up
                {0, 1},   // Right
                {1, 0},   // Down
                {0, -1},  // Left
        };
        int newX, newY;
        do {
            newX = curX + directions[towards][0];
            newY = curY + directions[towards][1];
            if (!isWithinBounds(newX, newY)) {
                return false;
            }
            if (stepCounter.contains(new Point3D(newX, newY, towards))) {
                return true;
            }
            stepCounter.add(new Point3D(newX, newY, towards));
            if (matrix[newX][newY] == '#') {
                towards = (towards + 1) % directions.length;
            } else {
                curX = newX;
                curY = newY;
            }
        } while (true);
    }

    private static boolean isWithinBounds(int newX, int newY) {
        return newX >= 0 && newX < dimensions && newY >= 0 && newY < dimensions;
    }

    public static void buildMatrix(String filePath) {
        matrix = new char[dimensions][dimensions];
        int cc = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    char temp = line.charAt(i);
                    matrix[cc][i] = line.charAt(i);
                    if (temp == '^') {
                        x = cc;
                        y = i;
                    }
                }
                cc++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public record Point3D(int x, int y, int z) {
    }
}
