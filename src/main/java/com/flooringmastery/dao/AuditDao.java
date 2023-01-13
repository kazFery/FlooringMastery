package com.flooringmastery.dao;

public interface AuditDao {

    public void writeAuditEntry(String entry) throws DataPersistenceException;

}
