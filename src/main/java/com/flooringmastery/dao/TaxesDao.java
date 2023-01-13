
package com.flooringmastery.dao;


import com.flooringmastery.dto.Tax;

import java.util.List;

public interface TaxesDao {

    Tax getState(String stateAbbr) throws DataPersistenceException;

    List<Tax> loadStates() throws DataPersistenceException;

}
