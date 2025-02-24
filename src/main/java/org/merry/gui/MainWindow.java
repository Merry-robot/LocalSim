package org.merry.gui;

import org.merry.simulation.CreateGAAircraft;
import org.merry.commands.CommandParser;
import org.merry.commands.*;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MainWindow {

    static CreateGAAircraft ac = new CreateGAAircraft();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create Main Window
            JFrame frame = new JFrame("Main Window");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 300);

            // Create Menu Bar
            JMenuBar menuBar = new JMenuBar();
            JMenu toolsMenu = new JMenu("Tools");
            JMenuItem openCommandWindow = new JMenuItem("Open Command Window");

            // Action Listener to Open Command Window
            openCommandWindow.addActionListener((ActionEvent e) -> {
                SwingUtilities.invokeLater(CommandParser::createCommandWindow);
            });

            // Add Menu Items
            toolsMenu.add(openCommandWindow);
            menuBar.add(toolsMenu);
            frame.setJMenuBar(menuBar);

            // Sample Labels to Show Data
            JPanel panel = new JPanel();
            JLabel callsignLabel = new JLabel("Callsign: " + CreateGAAircraft.returnCS());
            JLabel typeLabel = new JLabel("Type: " + CreateGAAircraft.returnType());
            JLabel statusLabel = new JLabel("Status: " + CreateGAAircraft.returnStatus());

            panel.add(callsignLabel);
            panel.add(typeLabel);
            panel.add(statusLabel);
            frame.add(panel);

            frame.setVisible(true);
        });
    }
}
