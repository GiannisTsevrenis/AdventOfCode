package Day4;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day4Part1 {
    private static char[][] grid;
    private static final int[][] directions = {
            {-1, 0},  // Up
            {1, 0},   // Down
            {0, -1},  // Left
            {0, 1},   // Right
            {-1, -1}, // Top-left diagonal
            {-1, 1},  // Top-right diagonal
            {1, -1},  // Bottom-left diagonal
            {1, 1}    // Bottom-right diagonal
    };
    private static final int rows = 140;
    private static final int cols = 140;

    public static void main(String[] args) {
        String filePath = "./aoc2024/Day4/Puzzle.txt";
        grid = getGridFromFileInput(filePath);
        int count = 0;
        int result;
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (grid[x][y] == 'X') { // when encounter X, search all 8 directions for possible XMAS
                    result = findXmas(x, y);
                    count += result;
                }
            }
        }
        System.out.println("XMAS TOTAL = " + count);
    }

    private static int findXmas(int x, int y) {
        int xmasCount = 0;
        String toFind = "MAS";
        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            int index = 0;
            for (int i = 0; i < 3; i++) {
                if (isWithinBounds(newX, newY) && (grid[newX][newY] == toFind.charAt(index))) {
                    index++;
                    if (index == 3) {
                        xmasCount++;
                    }
                    newX += direction[0];
                    newY += direction[1];
                } else {
                    break;
                }
            }
        }
        return xmasCount;
    }

    private static boolean isWithinBounds(int newX, int newY) {
        return newX >= 0 && newX < rows && newY >= 0 && newY < cols;
    }

    private static char[][] getGridFromFileInput(String filePath) {
        char[][] grid = new char[rows][cols];
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                for (int col = 0; col < cols; col++) {
                    grid[row][col] = line.charAt(col);
                }
                row++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return grid;
    }
}
