package com.gmail.luxdevpl.jointicket.listeners;

import com.gmail.luxdevpl.jointicket.MainClass;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class AsyncPlayerPreLoginList implements Listener {

    @EventHandler
    public void handle(AsyncPlayerPreLoginEvent e){
        if(!MainClass.getInstance().getConfiguration().configuration.playerCheckEnabled){
            return;
        }

        MainClass.getInstance().getTicketManager().checkForAuthrozationTicket(e.getAddress().getHostAddress());
    }

}
