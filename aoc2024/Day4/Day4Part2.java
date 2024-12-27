package Day4;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day4Part2 {
    private static char[][] grid;
    private static final int[][] directions = {
            {-1, 1},  // Top-right diagonal
            {-1, -1}, // Top-left diagonal
    };
    private static final int rows = 140;
    private static final int cols = 140;

    public static void main(String[] args) {
        String filePath = "./aoc2024/Day4/Puzzle.txt";
        grid = getGridFromFileInput(filePath);
        int count = 0;
        for (int x = 1; x < rows - 1; x++) {
            for (int y = 1; y < cols - 1; y++) {
                if (grid[x][y] == 'A') { // when encounter A, confirm the 4 corners
                    if (isDoubleXmas(x, y)) {
                        count++;
                    }
                }
            }
        }
        System.out.println("XMAS TOTAL = " + count);
    }

    private static boolean isDoubleXmas(int x, int y) {
        int countXmas = 0;
        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            int oppositeX = x + direction[0] * -1;
            int oppositeY = y + direction[1] * -1;
            if ((grid[newX][newY] == 'M') && (grid[oppositeX][oppositeY] == 'S')) {
                countXmas++;
            } else if ((grid[newX][newY] == 'S') && (grid[oppositeX][oppositeY] == 'M')) {
                countXmas++;
            }
        }
        return countXmas == 2;
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
