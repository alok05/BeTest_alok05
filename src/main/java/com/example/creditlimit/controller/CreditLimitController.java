package com.example.creditlimit.controller;

import com.example.creditlimit.logging.annotations.Log;
import com.example.creditlimit.logging.annotations.Profile;
import com.example.creditlimit.model.PersonInfo;
import com.example.creditlimit.service.CreditLimitService;
import com.example.creditlimit.utility.CommonUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Log
@Profile
@RequestMapping("/creditlimit")
@RestController
public class CreditLimitController {

    private final CreditLimitService creditLimitService;

    private final CommonUtility commonUtility;

    public CreditLimitController(CreditLimitService creditLimitService, CommonUtility commonUtility) {
        this.creditLimitService = creditLimitService;
        this.commonUtility = commonUtility;
    }

    @GetMapping(path = "/{source}")
    public ResponseEntity<Object> getPersonInfoWithCreditLimits(
            @PathVariable("source") String source) throws IOException {
        commonUtility.validateSource(source);
        List<PersonInfo> personInfoList = creditLimitService
                .getPersonInfoWithCreditLimits(source);

        return commonUtility.buildResponse(personInfoList, HttpStatus.OK);
    }
}
