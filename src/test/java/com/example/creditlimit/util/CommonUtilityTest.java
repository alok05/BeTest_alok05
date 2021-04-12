package com.example.creditlimit.util;

import com.example.creditlimit.exception.InvalidSourceException;
import com.example.creditlimit.utility.CommonUtility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CommonUtilityTest {

    @InjectMocks
    private CommonUtility commonUtility;

    @Test(expected = InvalidSourceException.class)
    public void testInvalidSource() throws InvalidSourceException {
        commonUtility.validateSource("ABC");
    }
}
