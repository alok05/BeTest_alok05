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
public class PRNReader implements SourceReader {

    private static final String PRN_FILE = "classpath:Workbook2.prn";

    private final CommonUtility commonUtility;

    public PRNReader(CommonUtility commonUtility) {
        this.commonUtility = commonUtility;
    }

    @Override
    public List<PersonInfo> getRecords() throws IOException {
        List<PersonInfo> personInfoOutputList = new ArrayList<>();

        InputStream inputStream = commonUtility.getResource(PRN_FILE);
        String dataAsString = StreamUtils.copyToString(inputStream, Charset.forName("ISO_8859_1"));

        String[] personInfoInputData = dataAsString.split("\n");
        String firstLineString = personInfoInputData[0];

        List<String> personInfoInputList = Arrays.asList(personInfoInputData).stream()
                .skip(1)
                .collect(Collectors.toList());

        String[] array = new String[7];
        for (String str : personInfoInputList) {
            array[0] = str.substring(firstLineString.indexOf("Name"), firstLineString.indexOf("Address")).trim();
            array[1] = "";
            array[2] = str.substring(firstLineString.indexOf("Address"), firstLineString.indexOf("Postcode")).trim();
            array[3] = str.substring(firstLineString.indexOf("Postcode"), firstLineString.indexOf("Phone")).trim();
            array[4] = str.substring(firstLineString.indexOf("Phone"), firstLineString.indexOf("Credit Limit")).trim();
            array[5] = str.substring(firstLineString.indexOf("Credit Limit"), firstLineString.indexOf("Birthday")).trim();
            array[6] = str.substring(firstLineString.indexOf("Birthday")).trim();

            array[6] = array[6].substring(6) + "/" + array[6].substring(4, 6) + "/" + array[6].substring(0, 4);
            personInfoOutputList.add(commonUtility.buildPersonInfo(array, SourceEnum.PRN.toString()));
        }

        return personInfoOutputList;
    }
}
