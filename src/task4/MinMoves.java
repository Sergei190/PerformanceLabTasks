package task4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class MinMoves {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java MinMoves <inputFile>");
            return;
        }

        String inputFile = args[0];

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            // Чтение чисел из файла
            String line;
            int[] nums = new int[100]; // Максимальное количество чисел, как указано в условии
            int count = 0;

            while ((line = reader.readLine()) != null) {
                nums[count++] = Integer.parseInt(line);
            }

            // Обрезаем массив до реального количества чисел
            nums = Arrays.copyOf(nums, count);

            // Вывод минимального количества ходов
            int minMoves = calculateMinMoves(nums);
            System.out.println(minMoves);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int calculateMinMoves(int[] nums) {
        Arrays.sort(nums);

        int median = nums[nums.length / 2];
        int moves = 0;

        for (int num : nums) {
            moves += Math.abs(num - median);
        }

        return moves;
    }

}