package com.mymoney.mapper;

import com.mymoney.models.MyMoneyData;
import org.junit.jupiter.api.Test;

import static com.mymoney.constant.TestConstants.TEST_FILE_PATH;
import static com.mymoney.faker.MyMoneyDataFaker.generateMyMoneyData;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MyMoneyMapperTest extends MyMoneyMapper {

    @Test
    void shouldConvertInputDataFromFileIntoMyMoneyDataObject() {
        MyMoneyData actual = getMyMoneyDataFromFile(TEST_FILE_PATH);
        MyMoneyData expected = generateMyMoneyData();
        assertEquals(expected.getPortfolio(), actual.getPortfolio());
        assertEquals(expected.getAssetClassData(), actual.getAssetClassData());
        assertEquals(expected.getActions().size(), actual.getActions().size());
    }

}