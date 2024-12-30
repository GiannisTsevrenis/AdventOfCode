package Day9;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
// my solution: 6347435485773

public class Day9Part2 {
    public static void main(String[] args) {
        String filePath = "./aoc2024/Day9/input.txt";//
        ArrayList<Integer> arraylist = buildBlocksList(filePath);//
        fillFreeSpaceBlocksWithWholeFiles(arraylist); // puts all digits at the start and returns index of last digit
        BigInteger res = BigInteger.valueOf(0);
        int current;
        for (int j = 0; j < arraylist.size(); j++) {
            current = arraylist.get(j);
            if (current >= 0) {
                res = res.add(BigInteger.valueOf((long) j * current));
            }
        }
        System.out.println(res);
    }

    private static void fillFreeSpaceBlocksWithWholeFiles(ArrayList<Integer> list) {
        //find last file ID
        int lastDigit = 1;
        while (list.get(list.size() - lastDigit) == -1) {
            lastDigit++;
        }
        int fileID = list.get(list.size() - lastDigit);
        int i = list.size() - 1;
        for (int id = fileID; id >= 0; id--) { // iterate all file ID numbers in decreasing order
            int currentIdNumberCount = 0;
            while (i > -1 && list.get(i) == id) { //count the current ID appearances
                currentIdNumberCount++;
                i--;
            }
            i++;
            // find if there is space for current group
            int j = 0;
            while (j < i) {
                int emptySpaceCount = 0;
                while (list.get(j) != -1 && (j < i)) { // skip numbers until empty space
                    j++;
                }
                while (list.get(j) == -1 && (j < i)) { // count empty spaces
                    emptySpaceCount++;
                    j++;
                }
                if (emptySpaceCount >= currentIdNumberCount) { // there is space, make swap and break
                    int spaceStart = j - emptySpaceCount;
                    int numberStart = i;
                    for (int k = 1; k <= currentIdNumberCount; k++) {
                        list.set(spaceStart, id);
                        list.set(numberStart, -1);
                        spaceStart++;
                        numberStart++;
                    }
                    break;
                }
            }
            // go to the next file ID group ( skip empty spaces )
            while (i > 0 && (list.get(i) != id - 1)) {
                i--;
            }
        }
    }

    public static ArrayList<Integer> buildBlocksList(String filePath) {
        int id = 0;
        ArrayList<Integer> result = new ArrayList<>();
        boolean isFile = true;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    if (isFile) {
                        for (int i = 0; i < c - '0'; i++) {
                            result.add(id);
                        }
                        id++;
                    } else {
                        for (int i = 0; i < c - '0'; i++) {
                            result.add(-1);
                        }
                    }
                    isFile = !isFile;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
