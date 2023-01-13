package com.flooringmastery.dao;

import com.flooringmastery.dto.Tax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StateDaoStubImpl implements TaxesDao{

    List<Tax> states = new ArrayList<Tax>();

    @Override
    public Tax getState(String stateAbbr) throws DataPersistenceException {
        Tax tax = null;
        if (stateAbbr.equalsIgnoreCase("Tx")){
            Tax tax1 = new Tax();
            tax1.setStateAbbreviation("TX");
            tax1.setStateName("Texas");
            tax1.setTaxRate(new BigDecimal("4.45"));
            tax = tax1;
        }
        if (stateAbbr.equalsIgnoreCase("WA")){
            Tax tax2 = new Tax();
            tax2.setStateAbbreviation("WA");
            tax2.setStateName("Washington");
            tax2.setTaxRate(new BigDecimal("9.25"));
            tax = tax2;

        }
        return tax;
    }

    @Override
    public List<Tax> loadStates() throws DataPersistenceException {
        Tax tax1 = new Tax();
        tax1.setStateAbbreviation("TX");
        tax1.setStateName("Texas");
        tax1.setTaxRate(new BigDecimal("4.45"));
        states.add(tax1);
        Tax tax2 = new Tax();
        tax2.setStateAbbreviation("WA");
        tax2.setStateName("Washington");
        tax2.setTaxRate(new BigDecimal("9.25"));
        states.add(tax2);

        return states;
    }
}
