package util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public static Double[] readDoubleColumn(String filePath, String columnName) throws IOException {

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setDelimiter(';')
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreSurroundingSpaces(true)
                .build();

        List<Double> doubleList = new ArrayList<>();

        try (Reader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, csvFormat)) {

            for (CSVRecord csvRecord : csvParser) {
                if (csvRecord.isMapped(columnName)) {
                    String value = csvRecord.get(columnName);
                    String cleanedValue = value.replace(',', '.').trim();

                    if (!cleanedValue.isEmpty()) {
                        try {
                            doubleList.add(Double.parseDouble(cleanedValue));
                        } catch (NumberFormatException e) {
                            System.err.println("Ignorando valor no numérico en fila " + csvRecord.getRecordNumber() + ": " + value);
                        }
                    }
                }
            }
        }
        return doubleList.toArray(new Double[0]);
    }
}