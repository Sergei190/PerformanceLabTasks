package task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CirclePosition {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java CirclePosition <circleFile> <pointsFile>");
            return;
        }

        String circleFile = args[0];
        String pointsFile = args[1];

        try {
            BufferedReader circleReader = new BufferedReader(new FileReader(circleFile));
            BufferedReader pointsReader = new BufferedReader(new FileReader(pointsFile));

            // Чтение координат и радиуса окружности из файла
            String[] circleCoords = circleReader.readLine().split(" ");
            double centerX = Double.parseDouble(circleCoords[0]);
            double centerY = Double.parseDouble(circleCoords[1]);
            double radius = Double.parseDouble(circleReader.readLine());

            String line;
            while ((line = pointsReader.readLine()) != null) {
                // Чтение координат точек из файла
                String[] pointCoords = line.split(" ");
                double pointX = Double.parseDouble(pointCoords[0]);
                double pointY = Double.parseDouble(pointCoords[1]);

                // Проверка положения точки относительно окружности
                int position = checkPointPosition(centerX, centerY, radius, pointX, pointY);
                System.out.println(position);
            }

            circleReader.close();
            pointsReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int checkPointPosition(double centerX, double centerY, double radius, double pointX, double pointY) {
        double distanceSquared = Math.pow(pointX - centerX, 2) + Math.pow(pointY - centerY, 2);

        if (distanceSquared < Math.pow(radius, 2)) {
            return 1; // Точка внутри окружности
        } else if (distanceSquared > Math.pow(radius, 2)) {
            return 2; // Точка снаружи окружности
        } else {
            return 0; // Точка лежит на окружности
        }
    }

}