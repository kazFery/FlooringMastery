package com.flooringmastery.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Tax {

    private String stateAbbreviation;
    private BigDecimal taxRate;
    private String stateName;


    public Tax() {
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tax tax = (Tax) o;
        return stateAbbreviation.equals(tax.stateAbbreviation)  && taxRate.equals(tax.taxRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stateAbbreviation,  taxRate);
    }

    @Override
    public String toString() {
        return "Tax{" +
                "stateAbbreviation='" + stateAbbreviation + '\'' +

                ", taxRate=" + taxRate +
                '}';
    }
}
