package org.example;

/**
 * # Weather Data Analyzer Application
 *
 * This application analyzes weather data from a CSV file and provides several functionalities:
 * - Calculate the average temperature for a specific month.
 * - List days with temperatures above a given threshold.
 * - Count the number of rainy days.
 *
 * It uses modern Java features:
 * - **Records** to represent immutable weather data.
 * - **Enhanced switch statements** for determining weather categories.
 * - **Text blocks** to generate formatted output.
 * - **Lambdas and Streams** for efficient data processing.
 * - **Pattern matching** and other improvements introduced from Java 15 to Java 23.
 *
 * ## CSV Format
 * The CSV file is expected to have the following columns:
 * `date,temperature,humidity,precipitation`
 * For example:
 * ```
 * 2023-05-01,25.3,60,0.0
 * 2023-05-02,27.8,55,1.2
 * ```
 */
public record WeatherData(String date, double temperature, double humidity, double precipitation) { }
