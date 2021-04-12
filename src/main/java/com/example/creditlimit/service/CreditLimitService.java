package com.example.creditlimit.service;

import com.example.creditlimit.model.PersonInfo;

import java.io.IOException;
import java.util.List;

public interface CreditLimitService {

    List<PersonInfo> getPersonInfoWithCreditLimits(String source) throws IOException;
}
