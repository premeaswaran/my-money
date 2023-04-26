package com.mymoney.models;

import com.mymoney.enums.AssetClass;
import lombok.Builder;
import lombok.Data;
import com.mymoney.models.action.Action;

import java.util.List;
import java.util.Map;

/**
 * This class represents the entire input data received from the input file including
 * Portfolio information, asset class related data and the actions that need to be performed on the inputs.
 *
 * @author Prem Kumar Easwaran
 */
@Data
@Builder
public class MyMoneyData {
    private Portfolio portfolio;
    private Map<AssetClass, AssetClassData<AssetClass>> assetClassData;
    private List<Action> actions;
}
