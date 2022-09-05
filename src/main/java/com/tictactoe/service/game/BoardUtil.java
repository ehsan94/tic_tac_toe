package com.tictactoe.service.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardUtil {
    private static final int NUMBER_ROWS = 3;
    private static final int NUMBER_COLUMNS = 3;

    public static List<List<String>> createEmpty() {
        List<List<String>> rows = new ArrayList<>();

        for (int rowIndex = 0; rowIndex < NUMBER_ROWS; rowIndex++) {
            List<String> row = new ArrayList<>();
            for (int columnIndex = 0; columnIndex < NUMBER_COLUMNS; columnIndex++) {
                row.add(BoardTile.EMPTY.toString());

            }
            rows.add(row);
        }

        return rows;
    }

    public static List<List<String>> getAllLines(List<List<String>> rows) {
        List<List<String>> lines = new ArrayList<>();

        for (int rowIndex = 0; rowIndex < NUMBER_ROWS; rowIndex++) {
            lines.add(rows.get(rowIndex));
        }

        for (int columnIndex = 0; columnIndex < NUMBER_COLUMNS; columnIndex++) {
            List<String> columnLine = new ArrayList<>();
            for (List<String> row : rows) {
                columnLine.add(row.get(columnIndex));
            }
            lines.add(columnLine);
        }

        List<String> diagonal1 = Arrays.asList(rows.get(0).get(0), rows.get(1).get(1), rows.get(2).get(2));
        lines.add(diagonal1);

        List<String> diagonal2 = Arrays.asList(rows.get(0).get(2), rows.get(1).get(1), rows.get(2).get(0));
        lines.add(diagonal2);

        return lines;
    }
}
