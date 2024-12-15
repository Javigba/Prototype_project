package main;

import services.TicketManager;
import ui.TicketManagerUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicketManager ticketManager = new TicketManager();
            TicketManagerUI ui = new TicketManagerUI(ticketManager);
            ui.setVisible(true);
        });
    }
}
