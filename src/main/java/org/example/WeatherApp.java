package org.example;

/**
 * This interface defines the weather data analysis functionalities.
 */


import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * The application entry point is implemented as a record.
 */
public record WeatherApp() {

    /**
     * Main method to run the Weather Data Analyzer Application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        String csvPath = "resources/weatherdata.csv";
        String csvData;
        try (InputStream is = WeatherApp.class.getClassLoader().getResourceAsStream(csvPath)) {
            if (is == null) {
                System.err.println("CSV file not found at: " + csvPath);
                return;
            }
            csvData = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
            return;
        }


        var weatherData = WeatherDataAnalyzer.parseCSV(csvData);

        double avgMayTemp = WeatherDataAnalyzer.averageTemperatureForMonth(weatherData, "05");
        var hotDays = WeatherDataAnalyzer.daysAboveTemperature(weatherData, 28);
        long rainyDays = WeatherDataAnalyzer.countRainyDays(weatherData);

        // Determine weather categories for each record
        var categories = weatherData.stream()
                .map(wd -> String.format("%s: %s", wd.date(), WeatherDataAnalyzer.weatherCategory(wd.temperature())))
                .toList();

        // Generate output using a text block
        String output = """
            Weather Analysis Report:
            ------------------------
            Average Temperature in May: %.2f °C
            Days with Temperature > 28°C: %s
            Number of Rainy Days: %d

            Weather Categories by Date:
            %s
            """.formatted(
                avgMayTemp,
                hotDays,
                rainyDays,
                String.join("\n", categories)
        );

        System.out.println(output);
    }
}
