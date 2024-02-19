package taak3;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TestReportGenerator {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java TestReportGenerator <valuesFile> <testsFile> <reportFile>");
            return;
        }

        String valuesFile = args[0];
        String testsFile = args[1];
        String reportFile = args[2];

        JSONParser jsonParser = new JSONParser();

        try {
            // Чтение результатов прохождения тестов
            JSONArray values = (JSONArray) jsonParser.parse(new FileReader(valuesFile));

            // Чтение структуры для отчета на основе прошедших тестов
            JSONObject testsStructure = (JSONObject) jsonParser.parse(new FileReader(testsFile));

            // Формирование результата и запись в файл report.json
            JSONArray reportValues = generateReportValues(values, testsStructure);
            writeReportToFile(reportFile, testsStructure, reportValues);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static JSONArray generateReportValues(JSONArray values, JSONObject testsStructure) {
        JSONArray reportValues = new JSONArray();

        for (Object testObject : testsStructure.values()) {
            JSONObject test = (JSONObject) testObject;
            String testId = (String) test.get("id");

            for (Object valueObject : values) {
                JSONObject value = (JSONObject) valueObject;
                String valueId = (String) value.get("id");

                if (testId.equals(valueId)) {
                    test.put("value", value.get("value"));
                    reportValues.add(test);
                    break;
                }
            }
        }

        return reportValues;
    }

    private static void writeReportToFile(String reportFile, JSONObject testsStructure, JSONArray reportValues) {
        JSONObject report = new JSONObject();
        report.putAll(testsStructure);
        report.put("values", reportValues);

        try (FileWriter fileWriter = new FileWriter(reportFile)) {
            fileWriter.write(report.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
