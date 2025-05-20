package org.example.services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class CsvReader {

    public static void readingCsv(String csvFile) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(csvFile), "ISO-8859-1"));
        ) {
            String line;
            boolean isFirstLine = true;

            while((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] headers = line.get(0);
            }
        }
    }
}
