package com.example.creditlimit.service;

import com.example.creditlimit.reader.CSVReader;
import com.example.creditlimit.reader.PRNReader;
import com.example.creditlimit.reader.SourceFactory;
import com.example.creditlimit.model.PersonInfo;
import com.example.creditlimit.model.SourceEnum;
import com.example.creditlimit.utility.CommonUtility;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


@RunWith(SpringRunner.class)
public class CreditServiceImplTest {

    @InjectMocks
    private CreditLimitServiceImpl creditLimitService;

    @InjectMocks
    private SourceFactory sourceFactory;

    @InjectMocks
    private CommonUtility commonUtility;

    @InjectMocks
    private CSVReader csvReader;

    @InjectMocks
    private PRNReader prnReader;

    @Autowired
    private ResourceLoader resourceLoader;

    private List<PersonInfo> personInfoList;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(creditLimitService, "sourceFactory", sourceFactory);
        ReflectionTestUtils.setField(sourceFactory, "prnReader", prnReader);
        ReflectionTestUtils.setField(sourceFactory, "csvReader", csvReader);
        ReflectionTestUtils.setField(csvReader, "commonUtility", commonUtility);
        ReflectionTestUtils.setField(prnReader, "commonUtility", commonUtility);
        ReflectionTestUtils.setField(commonUtility, "resourceLoader", resourceLoader);
    }

    @Test
    public void testNumberOfCSVRecords() throws IOException {
        personInfoList = creditLimitService.
                getPersonInfoWithCreditLimits(SourceEnum.CSV.toString());
        assertTrue(personInfoList.size() == 7);
        assertFalse(personInfoList.size() == 2);
    }

    @Test
    public void testNumberOfPRNRecords() throws IOException {
        personInfoList = creditLimitService.
                getPersonInfoWithCreditLimits(SourceEnum.PRN.toString());
        assertTrue(personInfoList.size() == 7);
        assertFalse(personInfoList.size() == 2);
    }

    @Test
    public void testCSVRecordsPersonInfoValues() throws IOException {
        personInfoList = creditLimitService.
                getPersonInfoWithCreditLimits(SourceEnum.CSV.toString());
        assertEquals("Johnson, John",personInfoList.get(0).getName());
        assertEquals("Mendelssohnstraat 54d",personInfoList.get(2).getAddress());
        assertEquals("87823",personInfoList.get(6).getPostCode());
        assertEquals("+44 728 889838",personInfoList.get(6).getPhoneNumber());
        assertEquals("63.6",personInfoList.get(5).getCreditLimit());
        assertEquals("01/01/1987",personInfoList.get(0).getBirthDay());
    }

    @Test
    public void testPRNRecordsPersonInfoValues() throws IOException {
        personInfoList = creditLimitService.
                getPersonInfoWithCreditLimits(SourceEnum.PRN.toString());
        assertEquals("Friendly, User",personInfoList.get(5).getName());
        assertEquals("Mendelssohnstraat 54d",personInfoList.get(2).getAddress());
        assertEquals("3122gg",personInfoList.get(0).getPostCode());
        assertEquals("020 3849381",personInfoList.get(0).getPhoneNumber());
        assertEquals("10909300",personInfoList.get(1).getCreditLimit());
        assertEquals("20/09/1999",personInfoList.get(6).getBirthDay());
    }
}
