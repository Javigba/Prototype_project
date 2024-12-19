package ui;

import models.EventTicket;
import models.Ticket;
import services.TicketManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TicketManagerUI extends JFrame {
    private final TicketManager ticketManager;
    private final DefaultListModel<String> listModel;

    public TicketManagerUI(TicketManager ticketManager) {
        this.ticketManager = ticketManager;
        this.listModel = new DefaultListModel<>();

        setTitle("Gestión de Tickets de Eventos");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de lista
        JList<String> ticketList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(ticketList);
        add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));

        JButton addTicketButton = new JButton("Añadir Ticket");
        JButton cloneTicketButton = new JButton("Clonar Ticket");
        JButton editTicketButton = new JButton("Editar Ticket");

        buttonPanel.add(addTicketButton);
        buttonPanel.add(cloneTicketButton);
        buttonPanel.add(editTicketButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Acciones de los botones
        addTicketButton.addActionListener(e -> showAddTicketDialog());
        cloneTicketButton.addActionListener(e -> showCloneTicketDialog(ticketList.getSelectedIndex()));
        editTicketButton.addActionListener(e -> showEditTicketDialog(ticketList.getSelectedIndex()));

        updateTicketList();
    }

    private void showAddTicketDialog() {
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        JTextField eventNameField = new JTextField();
        JTextField seatCategoryField = new JTextField();
        JTextField priceField = new JTextField();

        inputPanel.add(new JLabel("Nombre del Evento:"));
        inputPanel.add(eventNameField);
        inputPanel.add(new JLabel("Categoría del Asiento:"));
        inputPanel.add(seatCategoryField);
        inputPanel.add(new JLabel("Precio:"));
        inputPanel.add(priceField);

        int result = JOptionPane.showConfirmDialog(
                this,
                inputPanel,
                "Añadir Nuevo Ticket",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                String eventName = eventNameField.getText().trim();
                String seatCategory = seatCategoryField.getText().trim();
                double price = Double.parseDouble(priceField.getText().trim());

                if (eventName.isEmpty() || seatCategory.isEmpty()) {
                    throw new IllegalArgumentException("El nombre del evento y la categoría no pueden estar vacíos.");
                }

                Ticket newTicket = new EventTicket(eventName, seatCategory, price);
                ticketManager.addTicket(newTicket);
                updateTicketList();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El precio debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showCloneTicketDialog(int index) {
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un ticket para clonar.");
            return;
        }

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(new JLabel("Número de clones:"), BorderLayout.NORTH);

        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, 100, 1);
        JSpinner cloneCountSpinner = new JSpinner(spinnerModel);
        inputPanel.add(cloneCountSpinner, BorderLayout.CENTER);

        int result = JOptionPane.showConfirmDialog(
                this,
                inputPanel,
                "Clonar Ticket",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            int cloneCount = (int) cloneCountSpinner.getValue();
            cloneSelectedTicket(index, cloneCount);
        }
    }

    private void cloneSelectedTicket(int index, int cloneCount) {
        List<Ticket> tickets = ticketManager.getTickets();
        Ticket originalTicket = tickets.get(index);

        for (int i = 0; i < cloneCount; i++) {
            try {
                Ticket clonedTicket = ticketManager.cloneTicket(originalTicket);
                ticketManager.addTicket(clonedTicket);
            } catch (UnsupportedOperationException e) {
                JOptionPane.showMessageDialog(this, "El ticket no se puede clonar.");
                return;
            }
        }

        updateTicketList();
    }

    private void showEditTicketDialog(int index) {
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un ticket para editar.");
            return;
        }

        List<Ticket> tickets = ticketManager.getTickets();
        Ticket ticket = tickets.get(index);

        if (!(ticket instanceof EventTicket)) {
            JOptionPane.showMessageDialog(this, "El ticket seleccionado no es editable.");
            return;
        }

        EventTicket eventTicket = (EventTicket) ticket;

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        JTextField eventNameField = new JTextField(eventTicket.getEventName());
        JTextField seatCategoryField = new JTextField(eventTicket.getSeatCategory());
        JTextField priceField = new JTextField(String.valueOf(eventTicket.getPrice()));

        inputPanel.add(new JLabel("Nombre del Evento:"));
        inputPanel.add(eventNameField);
        inputPanel.add(new JLabel("Categoría del Asiento:"));
        inputPanel.add(seatCategoryField);
        inputPanel.add(new JLabel("Precio:"));
        inputPanel.add(priceField);

        int result = JOptionPane.showConfirmDialog(
                this,
                inputPanel,
                "Editar Ticket",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                String eventName = eventNameField.getText().trim();
                String seatCategory = seatCategoryField.getText().trim();
                double price = Double.parseDouble(priceField.getText().trim());

                if (eventName.isEmpty() || seatCategory.isEmpty()) {
                    throw new IllegalArgumentException("El nombre del evento y la categoría no pueden estar vacíos.");
                }

                Ticket updatedTicket = new EventTicket(eventName, seatCategory, price);
                ticketManager.updateTicket(index, updatedTicket);
                updateTicketList();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El precio debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateTicketList() {
        listModel.clear();
        for (Ticket ticket : ticketManager.getTickets()) {
            listModel.addElement(ticket.getDetails());
        }
    }
}
