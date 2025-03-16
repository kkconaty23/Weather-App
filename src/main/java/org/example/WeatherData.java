package org.example;

/**
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
