package Day10;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Day10Part2 {
    private static int n;
    private static int[][] grid;

    // my correct answer: 1255
    public static void main(String[] args) {
        String filePath = "./aoc2024/Day10/input.txt";
        buildmatrix(filePath); // 48x48
        int result = countAllTrailHeadsScore();
        System.out.println(result);
    }

    private static int countAllTrailHeadsScore() {
        int totalPaths = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean[][] visited = new boolean[n][n];
                if (grid[i][j] == 0) {
                    int result = dfs(i, j, 0, visited);
                    totalPaths += result;
                }
            }
        }
        return totalPaths;
    }

    private static int dfs(int i, int j, int currentNumber, boolean[][] visited) {
        if (currentNumber == 9) {
            return 1;
        }
        visited[i][j] = true;
        int[][] directions = {
                {-1, 0},  // Up
                {1, 0},   // Down
                {0, -1},  // Left
                {0, 1},   // Right
        };
        int newPaths = 0;
        for (int[] direction : directions) {
            int newX = i + direction[0];
            int newY = j + direction[1];
            if (isWithinBounds(newX, newY) && !visited[newX][newY] && (grid[newX][newY] == currentNumber + 1)) {
                newPaths += dfs(newX, newY, currentNumber + 1, visited);
            }
        }
        visited[i][j] = false;
        return newPaths;
    }

    private static boolean isWithinBounds(int newX, int newY) {
        return (newX >= 0 && newX < n && newY >= 0 && newY < n);
    }

    private static void buildmatrix(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            n = br.readLine().length();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int[][] matrix = new int[n][n];
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (int j = 0; j < line.toCharArray().length; j++) {
                    matrix[i][j] = line.charAt(j) - '0';
                }
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        grid = matrix;
    }
}
