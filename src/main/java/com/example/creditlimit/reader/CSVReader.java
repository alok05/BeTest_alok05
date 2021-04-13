package com.example.creditlimit.reader;

import com.example.creditlimit.model.PersonInfo;
import com.example.creditlimit.model.SourceEnum;
import com.example.creditlimit.utility.CommonUtility;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CSVReader implements SourceReader {

    private static final String DELIMITER = ",";

    private static final String CSV_FILE = "classpath:Workbook2.csv";

    private final CommonUtility commonUtility;

    public CSVReader(CommonUtility commonUtility) {
        this.commonUtility = commonUtility;
    }

    @Override
    public List<PersonInfo> getRecords() throws IOException {
        List<PersonInfo> personInfoOutputList = new ArrayList<PersonInfo>();

        InputStream inputStream = commonUtility.getResource(CSV_FILE);
        String dataAsString = StreamUtils.copyToString(inputStream, Charset.forName("ISO_8859_1"));

        List<String[]> personInfoInputList = Arrays.asList(dataAsString.split("\n"))
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
