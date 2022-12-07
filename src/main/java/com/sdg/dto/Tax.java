package com.sdg.dto;

import java.math.BigDecimal;

public class Tax {
    private String stateAbrr;
    private String stateName;
    private BigDecimal stateTaxRate;


    public String getStateAbrr() {
        return stateAbrr;
    }

    public void setStateAbrr(String state) {
        this.stateAbrr = state;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public BigDecimal getStateTaxRate() {
        return stateTaxRate;
    }

    public void setStateTaxRate(BigDecimal stateTaxRate) {
        this.stateTaxRate = stateTaxRate;
    }
}
