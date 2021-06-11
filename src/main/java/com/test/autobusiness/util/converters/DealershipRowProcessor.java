package com.test.autobusiness.util.converters;

import com.opencsv.processor.RowProcessor;

public class DealershipRowProcessor implements RowProcessor {

    @Override
    public String processColumnItem(String s) {
        return "goose";
    }

    @Override
    public void processRow(String[] row) {

        row[4] = processColumnItem(row[4]);
    }
}
