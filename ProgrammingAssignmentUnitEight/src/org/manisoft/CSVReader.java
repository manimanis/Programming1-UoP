package org.manisoft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cyberbox
 */
public class CSVReader implements Iterable<HashMap<String, String>> {
    
    public static final char COMMA_DELIMITER = ',';
    public static final char STRING_DELIMITER = '"';
    
    private char fieldsSeparator = COMMA_DELIMITER;
    
    private int columnsCount;
    private String[] columnNames;
    private String[] columnText = new String[256];
    private ArrayList<String[]> fileData = new ArrayList<>();
    
    public CSVReader(InputStream is) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            columnNames = readLine(br);
            String[] line;
            while ((line = readLine(br)) != null) {
                fileData.add(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(CSVReader.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    private void parseLine(String line) {
        columnsCount = (line.isEmpty()) ? 0 : 1;
        columnText[0] = "";
        int i = 0;
        while (i < line.length()) {
            if (line.charAt(i) == STRING_DELIMITER) {
                int j = i + 1;
                while (j < line.length() && line.charAt(j) != '"') {
                    j++;
                }
                if (line.charAt(j) == '"') {
                    j++;
                }
                columnText[columnsCount - 1] += line.substring(i+1, j-1);
                i = j;
            } else if (line.charAt(i) == fieldsSeparator) {
                columnsCount++;
                columnText[columnsCount - 1] = "";
                i++;
            } else {
                columnText[columnsCount - 1] += line.charAt(i++);
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
    
    public HashMap<String, String> getAsHash(int lineIndex) {
        if (lineIndex >= 0 && lineIndex < fileData.size()) {
            HashMap<String, String> data = new HashMap<>();
            String[] line = fileData.get(lineIndex);
            for (int i = 0; i < columnsCount; i++) {
                data.put(columnNames[i], line[i]);
            }
            return data;
        }
        return null;
    }

    @Override
    public Iterator<HashMap<String, String>> iterator() {
        return new Iterator<HashMap<String, String>>() {
            int currIndex = 0;
            @Override
            public boolean hasNext() {
                return currIndex < fileData.size();
            }

            @Override
            public HashMap<String, String> next() {
                return getAsHash(currIndex++);
            }
        };
    }

    
    
}
