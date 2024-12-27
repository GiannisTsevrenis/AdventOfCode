package Day2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Day2Part2 {
    public static void main(String[] args) {
        String filePath = "./aoc2024/Day2/input1.txt";
        int sum = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                int[] array = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                Object result1 = isDecreasing(array);
                Object result2 = isIncreasing(array);
                if (result1 instanceof Boolean || result2 instanceof Boolean) {
                    sum++;
                } else {
                    ArrayList<?> decreaseList = (ArrayList<?>) result1;
                    ArrayList<?> increaseList = (ArrayList<?>) result2;
                    if ((int) decreaseList.get(0) > (int) increaseList.get(0)) {
                        if (decreaseWithLevelRemoval(array, decreaseList.get(1))) {
                            sum++;
                        }
                    } else {
                        if (increaseWithLevelRemoval(array, increaseList.get(1))) {
                            sum++;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sum);
    }

    private static boolean decreaseWithLevelRemoval(int[] array, Object o) {
        HashSet<Integer> hashSet = new HashSet<>();
        ArrayList<?> integers = (ArrayList<?>) o;
        boolean result = false;
        for (Object integer : integers) {
            hashSet.add(((Integer) integer) - 1);
            hashSet.add((Integer) integer);
        }
        ArrayList<Integer> originalList = Arrays.stream(array).boxed().collect(Collectors.toCollection(ArrayList::new));
        for (Integer integer : hashSet) {
            ArrayList<Integer> temp = new ArrayList<>(originalList);
            temp.remove((int)integer);
            if (isDecreasing(temp)) {
                result = true;
            }
        }
        return result;
    }

    private static boolean increaseWithLevelRemoval(int[] array, Object o) {
        HashSet<Integer> hashSet = new HashSet<>();
        ArrayList<?> integers = (ArrayList<?>) o;
        boolean result = false;
        for (Object integer : integers) {
            hashSet.add(((Integer) integer) - 1);
            hashSet.add((Integer) integer);
        }
        ArrayList<Integer> originalList = Arrays.stream(array).boxed().collect(Collectors.toCollection(ArrayList::new));
        for (Integer integer : hashSet) {
            ArrayList<Integer> temp = new ArrayList<>(originalList);
            temp.remove((int)integer);
            if (isIncreasing(temp)) {
                result = true;
            }
        }
        return result;
    }

    public static Boolean isDecreasing(ArrayList<Integer> nums) {
        for (int i = 1; i < nums.size(); i++) {
            if ((nums.get(i) < nums.get(i - 1)) && ((nums.get(i - 1) - nums.get(i))) <= 3) {
            } else {
                return false;
            }
        }
        return true;
    }

    public static Boolean isIncreasing(ArrayList<Integer> nums) {
        for (int i = 1; i < nums.size(); i++) {
            if ((nums.get(i) > nums.get(i - 1)) && ((nums.get(i) - nums.get(i - 1))) <= 3) {
            } else {
                return false;
            }
        }
        return true;
    }

    public static Object isDecreasing(int[] nums) {
        int decreasingRank = 0;
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 1; i < nums.length; i++) {
            if ((nums[i] < nums[i - 1]) && ((nums[i - 1] - nums[i])) <= 3) {
                decreasingRank++;
            } else {
                integers.add(i);
            }
        }
        if (decreasingRank == nums.length - 1) {
            return true;
        } else {
            return new ArrayList<>(Arrays.asList(decreasingRank, integers));
        }
    }

    public static Object isIncreasing(int[] nums) {
        int increasingRank = 0;
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 1; i < nums.length; i++) {
            if ((nums[i] > nums[i - 1]) && ((nums[i] - nums[i - 1])) <= 3) {
                increasingRank++;
            } else {
                integers.add(i);
            }
        }
        if (increasingRank == nums.length - 1) {
            return true;
        } else {
            return new ArrayList<>(Arrays.asList(increasingRank, integers));
        }
    }
}
