package com.panels;

import com.display.DisplayPanel;
import com.ticket.ParkingTicket;

import java.util.Date;

public class OutPanel {
    private String panelId;
    private DisplayPanel displayPanel;

    public OutPanel(String panelId, DisplayPanel displayPanel) {
        this.panelId = panelId;
        this.displayPanel = displayPanel;
    }

    public String getPanelId() {
        return panelId;
    }

    public void setPanelId(String panelId) {
        this.panelId = panelId;
    }

    public DisplayPanel getDisplayPanel() {
        return displayPanel;
    }

    public void setDisplayPanel(DisplayPanel displayPanel) {
        this.displayPanel = displayPanel;
    }

    public double calculateAmount(ParkingTicket ticket) {
        long entryTime = ticket.getEntryTime().getTime();
        long outTime = ticket.getOutTime().getTime();
        long duration = outTime - entryTime;
        long hours = duration / 3600000;
        return hours * 10;

    }

    public ParkingTicket checkOut(ParkingTicket ticket){
        ticket.setOutTime(new Date());
        ticket.setPaid(true);
        ticket.setAmount(calculateAmount(ticket));
        return ticket;

    }
}
