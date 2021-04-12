package com.example.creditlimit.utility;

import com.example.creditlimit.exception.InvalidSourceException;
import com.example.creditlimit.model.PersonInfo;
import com.example.creditlimit.model.ServiceResponse;
import com.example.creditlimit.model.SourceEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;

@Component
public class CommonUtility {

    public String getResource(String fileName) throws FileNotFoundException {
        URL resource = getClass().getClassLoader().getResource(fileName);
        Objects.requireNonNull(resource);
        return resource.getFile();
    }

    public PersonInfo buildPersonInfo(String[] array, String source) {
        String name = source.equals(SourceEnum.CSV.toString()) ? array[0].substring(array[0].indexOf("\"") + 1)
                + ","
                + array[1].substring(0, array[1].lastIndexOf("\"")) :
                source.equals(SourceEnum.PRN.toString()) ? array[0] : "";

        return PersonInfo.builder()
                .name(name)
                .address(array[2])
                .postCode(array[3])
                .phoneNumber(array[4])
                .creditLimit(array[5])
                .birthDay(array[6])
                .build();
    }

    public ResponseEntity<Object> buildResponse(Object object, HttpStatus httpStatus) {
        String status = httpStatus.equals(HttpStatus.OK) ? "success" : "failed";

        return new ResponseEntity<Object>(
                ServiceResponse.builder()
                        .status(status)
                        .data(object)
                        .build(),
                httpStatus);
    }

    public boolean validateSource(String source) throws InvalidSourceException {
        if (source != null && !Arrays.asList(SourceEnum.CSV.toString(), SourceEnum.PRN.toString())
                .contains(source)) {
            throw new InvalidSourceException("Please provide the valid source");
        }
        return true;
    }
}
