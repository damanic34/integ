package com.ship.track.GenInvoice.UI;

import com.ship.track.GenInvoice.Service.ShipmentManagementSystem;

import javax.swing.*;
import java.awt.*;

class EmailSettingsDialog extends JDialog {
    private JTextField hostField, portField, emailField;
    private JPasswordField passwordField;
    private ShipmentManagementSystem sms;

    public EmailSettingsDialog(Frame owner, ShipmentManagementSystem sms) {
        super(owner, "Email Settings", true);
        this.sms = sms;

        setSize(400, 250);
        setLocationRelativeTo(owner);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("SMTP Host:"));
        hostField = new JTextField("smtp.gmail.com");
        panel.add(hostField);

        panel.add(new JLabel("SMTP Port:"));
        portField = new JTextField("587");
        panel.add(portField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField("company@example.com");
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            saveSettings();
            dispose();
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());

        panel.add(saveButton);
        panel.add(cancelButton);

        add(panel);
    }

    private void saveSettings() {
        String host = hostField.getText();
        String port = portField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        sms.updateEmailSettings(host, port, email, password);

        JOptionPane.showMessageDialog(this,
                "Email settings updated successfully!",
                "Settings Saved",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
