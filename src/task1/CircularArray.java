package task1;

public class CircularArray {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java CircularArray <n> <m>");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        // Проверка на корректные значения n и m
        if (n <= 0 || m <= 0) {
            System.out.println("Invalid values for n and m. Both should be greater than 0.");
            return;
        }

        int[] circularArray = new int[n];
        for (int i = 0; i < n; i++) {
            circularArray[i] = i + 1;
        }

        int currentIndex = 0;
        StringBuilder path = new StringBuilder();

        for (int i = 0; i < n; i++) {
            currentIndex = (currentIndex + m - 1) % n;
            path.append(circularArray[currentIndex]).append(" ");

            // Удаляем текущий элемент из массива
            circularArray = removeElement(circularArray, currentIndex);
            n--;

            if (n == 0) {
                break;
            }
        }

        System.out.println("Circular Array: " + arrayToString(circularArray));
        System.out.println("Path: " + path.toString().trim());
    }

    private static int[] removeElement(int[] array, int index) {
        int[] newArray = new int[array.length - 1];
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);
        return newArray;
    }

    private static String arrayToString(int[] array) {
        StringBuilder result = new StringBuilder();
        for (int value : array) {
            result.append(value);
        }
        return result.toString();
    }

}