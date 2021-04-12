package com.example.creditlimit.service;

import com.example.creditlimit.logging.annotations.Log;
import com.example.creditlimit.logging.annotations.Profile;
import com.example.creditlimit.model.PersonInfo;
import com.example.creditlimit.reader.SourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CreditLimitServiceImpl implements CreditLimitService {

    @Autowired
    private SourceFactory sourceFactory;

    @Override
    public List<PersonInfo> getPersonInfoWithCreditLimits(String source) throws IOException {

        return sourceFactory.getSource(source).getRecords();
    }
}
