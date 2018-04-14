package com.gmail.luxdevpl.jointicket;

import com.gmail.luxdevpl.jointicket.configuration.TicketConfiguration;
import com.gmail.luxdevpl.jointicket.configuration.util.ConfigUtil;
import com.gmail.luxdevpl.jointicket.listeners.AsyncPlayerPreLoginList;
import com.gmail.luxdevpl.jointicket.listeners.PlayerLoginList;
import com.gmail.luxdevpl.jointicket.manager.TicketManager;
import com.gmail.luxdevpl.jointicket.storage.database.MySQLDatabase;
import com.gmail.luxdevpl.jointicket.storage.impl.MySQLStorageImpl;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Optional;

public class MainClass extends JavaPlugin {

    private static MainClass instance;

    private MySQLDatabase mySql;

    private TicketConfiguration configuration;

    private TicketManager ticketManager;

    @Override
    public void onLoad(){
        instance = this;

        if(!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
        }
    }

    @Override
    public void onEnable() {
        //Initializing configuration
        this.initConfiguration();

        //Initializing storage
        this.initStorage();

        //Registering listeners
        this.registerEvents();

        this.ticketManager = new TicketManager();
    }

    @Override
    public void onDisable() {
        this.mySql.close();
    }

    private void initConfiguration(){
        this.configuration = ConfigUtil.loadConfig(new File("plugins/MC-JoinTicket/config.yml"), TicketConfiguration.class);
    }

    private void initStorage(){
        //Todo add SQLite support.
        this.mySql = new MySQLStorageImpl(this);

        try{
            this.mySql.open();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void registerEvents(){
        this.getServer().getPluginManager().registerEvents(new AsyncPlayerPreLoginList(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerLoginList(), this);
    }

    public Optional<MySQLDatabase> getStorage() {
        return Optional.ofNullable(this.mySql);
    }

    public static MainClass getInstance() {
        return instance;
    }

    public TicketConfiguration getConfiguration() {
        return configuration;
    }

    public TicketManager getTicketManager() {
        return ticketManager;
    }

}
