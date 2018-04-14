package com.gmail.luxdevpl.jointicket.storage.impl;

import com.gmail.luxdevpl.jointicket.MainClass;
import com.gmail.luxdevpl.jointicket.configuration.TicketConfiguration;
import com.gmail.luxdevpl.jointicket.storage.AbstractDatabase;
import com.gmail.luxdevpl.jointicket.storage.database.MySQLDatabase;
import com.gmail.luxdevpl.jointicket.storage.util.Query;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLStorageImpl extends AbstractDatabase implements MySQLDatabase {

    private HikariDataSource dataSource;

    private MainClass mainInstance;

    public MySQLStorageImpl(MainClass core) {
        super(core);

        this.mainInstance = core;
    }

    @Override
    protected void create(TicketConfiguration configuration) {
        this.dataSource = new HikariDataSource();

        this.dataSource.setMaximumPoolSize((Runtime.getRuntime().availableProcessors() * 2) + 1);

        this.dataSource.setJdbcUrl("jdbc:mysql://" + configuration.mySQLConfiguration.mysqlIp + ":" + configuration.mySQLConfiguration.port + "/" + configuration.mySQLConfiguration
                .databaseName);

        this.dataSource.setUsername(configuration.mySQLConfiguration.mysqlUser);
        this.dataSource.setPassword(configuration.mySQLConfiguration.mysqlPasssword);

        this.dataSource.addDataSourceProperty("cachePrepStmts", true);
        this.dataSource.addDataSourceProperty("prepStmtCacheSize", 250);
        this.dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        this.dataSource.addDataSourceProperty("useServerPrepStmts", true);

        this.initTable();

        mainInstance.getLogger().info("Connection with database has been initialized.");
    }

    @Override
    protected void destroy(TicketConfiguration configuration) {
        this.dataSource.close();

        mainInstance.getLogger().info("Connection with database has been closed.");
    }

    private void initTable() {
        try (Connection connection = this.dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(Query.TABLE_TICKET_QUERY)) {
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void checkForAuthorization(String address) {
        try (Connection connection = this.dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(Query.CHECK_TICKET_QUERY)) {
            preparedStatement.setString(1, address);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    MainClass.getInstance().getTicketManager().addTicket(rs.getString("ip"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
