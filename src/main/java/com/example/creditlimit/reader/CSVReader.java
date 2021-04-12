package com.example.creditlimit.reader;

import com.example.creditlimit.model.PersonInfo;
import com.example.creditlimit.model.SourceEnum;
import com.example.creditlimit.utility.CommonUtility;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CSVReader implements SourceReader {

    private static final String DELIMITER = ",";

    private static final String CSV_FILE = "Workbook2.csv";

    private final CommonUtility commonUtility;

    public CSVReader(CommonUtility commonUtility) {
        this.commonUtility = commonUtility;
    }

    @Override
    public List<PersonInfo> getRecords() throws IOException {
        List<PersonInfo> personInfoOutputList = new ArrayList<PersonInfo>();

        List<String[]> personInfoInputList = Files
                .readAllLines(new File(commonUtility.getResource(CSV_FILE)).toPath(), Charset.forName("ISO_8859_1"))
                .stream()
                .skip(1)
                .map(s -> s.split(DELIMITER))
                .collect(Collectors.toList());

        for (String[] array : personInfoInputList) {
            personInfoOutputList.add(commonUtility.buildPersonInfo(array, SourceEnum.CSV.toString()));
        }

        return personInfoOutputList;
    }
}
