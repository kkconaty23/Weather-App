package org.example;

public interface WeatherDataAnalyzer {
    static java.util.List<WeatherData> parseCSV(String csvContent) {
        return csvContent.strip().lines()
                // Skip header if present; adjust as needed.
                .skip(csvContent.startsWith("date") ? 1 : 0)
                .map(line -> line.split(","))
                .map(parts -> new WeatherData(
                        parts[0].strip(),
                        Double.parseDouble(parts[1].strip()),
                        Double.parseDouble(parts[2].strip()),
                        Double.parseDouble(parts[3].strip())
                ))
                .toList();
    }

    /**
     * Calculates the average temperature for a given month.
     *
     * @param data  the list of WeatherData records
     * @param month the month in "MM" format (e.g., "05")
     * @return the average temperature
     */
    static double averageTemperatureForMonth(java.util.List<WeatherData> data, String month) {
        return data.stream()
                .filter(wd -> wd.date().substring(5, 7).equals(month))
                .mapToDouble(WeatherData::temperature)
                .average()
                .orElse(Double.NaN);
    }

    /**
     * Returns a list of dates where the temperature is above the threshold.
     *
     * @param data      the list of WeatherData records
     * @param threshold temperature threshold
     * @return list of dates as Strings
     */
    static java.util.List<String> daysAboveTemperature(java.util.List<WeatherData> data, double threshold) {
        return data.stream()
                .filter(wd -> wd.temperature() > threshold)
                .map(WeatherData::date)
                .toList();
    }

    /**
     * Counts the number of rainy days (precipitation > 0).
     *
     * @param data the list of WeatherData records
     * @return the count of rainy days
     */
    static long countRainyDays(java.util.List<WeatherData> data) {
        return data.stream()
                .filter(wd -> wd.precipitation() > 0)
                .count();
    }

    /**
     * Determines a weather category based on temperature using an enhanced switch.
     *
     * @param temperature the temperature value
     * @return the weather category as a String ("Cold", "Warm", or "Hot")
     */
    static String weatherCategory(double temperature) {
        return switch ((int) temperature / 10) {
            case 0, 1, 2, 3 -> "Cold";
            case 4, 5, 6 -> "Warm";
            case 7, 8, 9, 10 -> "Hot";
            default -> "Unknown";
        };
    }
}

