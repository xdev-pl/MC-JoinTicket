package com.gmail.luxdevpl.jointicket.storage.util;

import com.gmail.luxdevpl.jointicket.MainClass;

public final class Query {

    /*
     * Table queries
     */

    public static String TABLE_TICKET_QUERY =
            "CREATE TABLE IF NOT EXISTS tickets (ip varchar(15) not null);";

    public static String DATABASE_QUERY =
            "CREATE DATABASE IF NOT EXISTS " + MainClass.getInstance().getConfiguration().mySQLConfiguration.databaseName;

    /*
     * Auth ticket check query
     */

    public static String CHECK_TICKET_QUERY = "SELECT * FROM tickets WHERE ip = ?";


}
