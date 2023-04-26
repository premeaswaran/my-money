package com.mymoney.models;

import com.mymoney.enums.AssetClass;
import com.mymoney.enums.Month;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.mymoney.constant.TestConstants.TEST_CHANGE_RATE_1;
import static com.mymoney.constant.TestConstants.TEST_CHANGE_RATE_2;
import static com.mymoney.faker.AssetClassDataFaker.generateAssetClassData_Equity;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AssetClassDataTest {
    private AssetClassData<AssetClass> assetClassData;

    @BeforeEach
    void setup() {
        assetClassData = generateAssetClassData_Equity();
    }

    @Test
    void shouldReturnChangeRateForGivenMonth() {
        assertEquals(TEST_CHANGE_RATE_1, assetClassData.getChangeRateForMonth(Month.JANUARY));
        assertEquals(TEST_CHANGE_RATE_2, assetClassData.getChangeRateForMonth(Month.FEBRUARY));
    }

}