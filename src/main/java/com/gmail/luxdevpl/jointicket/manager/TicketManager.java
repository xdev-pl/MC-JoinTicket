package com.gmail.luxdevpl.jointicket.manager;

import com.gmail.luxdevpl.jointicket.MainClass;

import java.util.LinkedList;
import java.util.List;

public class TicketManager {

    private final List<String> tickets = new LinkedList<>();

    public void checkForAuthrozationTicket(String address){
        MainClass.getInstance().getStorage().ifPresent(mySQLDatabase -> mySQLDatabase.checkForAuthorization(address));
    }

    public boolean hasAuthorizationTicket(String address){
        return tickets.contains(address);
    }

    public void addTicket(String address){
        this.tickets.add(address);
    }

    public void removeTicket(String address){
        this.tickets.remove(address);
    }

}
