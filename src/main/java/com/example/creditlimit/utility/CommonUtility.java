package com.example.creditlimit.utility;

import com.example.creditlimit.exception.InvalidSourceException;
import com.example.creditlimit.model.PersonInfo;
import com.example.creditlimit.model.ServiceResponse;
import com.example.creditlimit.model.SourceEnum;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@Component
public class CommonUtility {

    private final ResourceLoader resourceLoader;

    public CommonUtility(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public InputStream getResource(String fileName) throws IOException {

        Resource resource = resourceLoader.getResource(fileName);
        return resource.getInputStream();
    }

    public PersonInfo buildPersonInfo(String[] array, String source) {
        String name = source.equals(SourceEnum.CSV.toString()) ? array[0].substring(array[0].indexOf("\"") + 1)
                + ","
                + array[1].substring(0, array[1].lastIndexOf("\"")) :
                source.equals(SourceEnum.PRN.toString()) ? array[0] : "";

        return PersonInfo.builder()
                .name(name)
                .address(array[2].trim())
                .postCode(array[3].trim())
                .phoneNumber(array[4].trim())
                .creditLimit(array[5].trim())
                .birthDay(array[6].trim())
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
