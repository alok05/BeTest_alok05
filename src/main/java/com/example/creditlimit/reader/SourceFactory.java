package com.example.creditlimit.reader;

import com.example.creditlimit.exception.InvalidSourceException;
import com.example.creditlimit.model.SourceEnum;
import org.springframework.stereotype.Component;

@Component
public class SourceFactory {

    private final CSVReader csvReader;

    private final PRNReader prnReader;

    public SourceFactory(CSVReader csvReader, PRNReader prnReader) {
        this.csvReader = csvReader;
        this.prnReader = prnReader;
    }

    public SourceReader getSource(String source) {

        switch (SourceEnum.valueOf(source)) {
            case CSV:
                return csvReader;
            case PRN:
                return prnReader;
            default:
                throw new InvalidSourceException("Invalid source");
        }
    }
}


