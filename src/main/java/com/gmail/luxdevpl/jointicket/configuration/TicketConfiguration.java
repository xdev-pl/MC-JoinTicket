package com.gmail.luxdevpl.jointicket.configuration;

import com.google.common.collect.ImmutableList;
import org.diorite.cfg.annotations.CfgClass;
import org.diorite.cfg.annotations.CfgCollectionStyle;
import org.diorite.cfg.annotations.CfgComment;
import org.diorite.cfg.annotations.CfgName;
import org.diorite.cfg.annotations.defaults.CfgDelegateDefault;

import java.util.List;

@CfgClass(name = "TicketConfiguration")
@CfgDelegateDefault("{new}")

@CfgComment(" ")
@CfgComment(" ")
@CfgComment("Konfiguracja pluginu MC-JoinTicket")
@CfgComment("Wersja pluginu: 1.0 ")
@CfgComment(" ")
@CfgComment(" ")


public class TicketConfiguration {

    @CfgComment("Konfiguracja bazy danych")
    @CfgName("mysql-configuration-settings")
    public DatabaseConfiguration mySQLConfiguration = new DatabaseConfiguration();

    @CfgComment("Konfiguracja pluginu.")
    @CfgName("plugin-configuration-settings")
    public PluginConfiguration configuration = new PluginConfiguration();

    public static class DatabaseConfiguration {

        @CfgComment("Adres serwera mysql")
        @CfgComment("mysql-address")
        public String mysqlIp = "localhost";

        @CfgComment("Port serwera zdalnego mysql")
        @CfgName("mysql-port")
        public String port = "3306";

        @CfgComment("Nazwa bazy danych")
        @CfgName("mysql-database")
        public String databaseName = "jointicket";

        @CfgComment("Uzytkownik bazy danych")
        @CfgName("mysql-user")
        public String mysqlUser = "root";

        @CfgComment("Haslo do uzytkownika bazy danych")
        @CfgName("mysql-password")
        public String mysqlPasssword = "pwd";

    }

    public static class PluginConfiguration {

        @CfgComment("Czy plugin ma sprawdzac graczy przy wchodzeniu?")
        @CfgName("plugin-player-check-enabled")
        public boolean playerCheckEnabled = true;

        @CfgComment("Wiadomosc gdy gracz nie jest w bazie sprawdzonych graczy")
        @CfgName("kick-message")
        @CfgCollectionStyle(CfgCollectionStyle.CollectionStyle.ALWAYS_NEW_LINE)
        public List<String> kickMessage = ImmutableList.<String> builder()
                .add("&3Aby wejsc na nasz serwer musisz sie najpierw zweryfikowac.")
                .add("W tym celu odwiedz nasza strone i kliknij jeden przycisk.")
                .add(" ")
                .add("&7https://twojastrona.pl/check/")
                .build();
    }

}