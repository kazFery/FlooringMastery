package com.flooringmastery.dao;

import com.flooringmastery.dto.Product;
import com.flooringmastery.dto.Tax;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.nimbus.State;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaxesDaoFileImplTest {

    TaxesDao testTaxDao;


    @Test
    void getState() throws DataPersistenceException {

        testTaxDao = new TaxesDaoFileImpl();
        List<Tax> states  = testTaxDao.loadStates();

        Tax state1 = new Tax();
        state1.setStateName("Texas");
        state1.setStateAbbreviation("TX");
        state1.setTaxRate(new BigDecimal("4.45"));

        states.add(state1);
        assertTrue(state1.getStateName().equals(testTaxDao.getState("TX").getStateName()));


    }

    @Test
    void loadStates() throws DataPersistenceException {

        testTaxDao = new TaxesDaoFileImpl();
        List<Tax> states  = testTaxDao.loadStates();

        Tax state1 = new Tax();
        state1.setStateName("Texas");
        state1.setStateAbbreviation("TX");
        state1.setTaxRate(new BigDecimal("4.45"));

        states.add(state1);
        assertTrue(state1.getStateName().equals(states.get(0).getStateName()));


    }

}