package org.manisoft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.manisoft.entities.Employee;

/**
 *
 * @author Cyberbox
 */
public class CSVReader {

    private int columnsCount;
    private String[] columnText = new String[256];

    public static final char COMMA_DELIMITER = ',';
    private String[] columnNames;

    public CSVReader(InputStream is) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            columnNames = readLine(br);
            String[] line;
            while ((line = readLine(br)) != null) {
                Employee employee = new Employee(line);
                System.out.println(employee);
            }
        } catch (IOException ex) {
            Logger.getLogger(CSVReader.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    private void parseLine(String line) {
        columnsCount = 0;
        columnText[0] = "";
        int i = 0;
        while (i < line.length()) {
            if (line.charAt(i) == '"') {
                int j = i+1;
                while (j < line.length() && line.charAt(j) != '"') {
                    j++;
                }
                columnText[columnsCount] += line.substring(i, j);
                i = j;
            } else if (line.charAt(i) == ',') {
                columnsCount++;
                columnText[columnsCount] = "";
                i++;
            } else {
                columnText[columnsCount] += line.charAt(i++);
            }
        }
    }

    private String[] readLine(BufferedReader br) throws IOException {
        String line = br.readLine();
        if (line != null) {
            parseLine(line);
            return Arrays.copyOf(columnText, columnsCount);
        }
        return null;
    }

}
