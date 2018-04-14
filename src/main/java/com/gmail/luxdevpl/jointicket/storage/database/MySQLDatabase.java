package com.gmail.luxdevpl.jointicket.storage.database;

import com.gmail.luxdevpl.jointicket.storage.util.IDatabase;

public interface MySQLDatabase extends IDatabase {

    /**
     * checks if address has an authorization ticket in the storage
     * @param address ip address.
     */
    void checkForAuthorization(String address);
}
