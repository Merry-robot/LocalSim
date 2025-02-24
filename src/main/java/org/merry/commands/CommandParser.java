package org.merry.commands;

import com.beust.jcommander.*;
import javax.swing.*;
import java.util.*;
import java.util.List;


interface CommandInterface {
    void execute();
}



@Parameters(commandNames = {"CTO", "cto"}, commandDescription = "Clears an aircraft for takeoff.")
class CTOCommand implements CommandInterface {
    @Parameter(description = "Initial departure heading", required = false)
    private List<String> heading;

    @Override
    public void execute() {
        String headingValue = (heading != null && !heading.isEmpty()) ? heading.get(0) : "Runway heading/SID";
        System.out.println("Cleared for takeoff. Heading: " + headingValue);
    }
}
// EXIT Command
@Parameters(commandNames = {"EXIT", "exit"}, commandDescription = "Instructs an aircraft to exit at a specific taxiway/runway.")
class ExitCommand implements CommandInterface {
    @Parameter(description = "Taxiway/Runway to exit at", required = true)
    private String taxiway;

    @Override
    public void execute() {
        System.out.println("Exit at " + taxiway);
    }
}

@Parameters(commandNames = {"FS", "fs"}, commandDescription = "Instructs an aircraft to make a full stop on the assigned runway")
class FullStopCommand implements CommandInterface {
    @Parameter(description = "Runway to land on", required = false)
    private List<String> runway;

    @Override
    public void execute() {
        System.out.println("Cleared to land");
    }
}

@Parameters(commandNames = {"TG", "tg"}, commandDescription = "Instructs and aircraft to make a touch and go on the assigned runway")
class TGCommand implements CommandInterface {
    @Parameter(description = "Runway to land on", required = false)
    private  List<String> runway;

    @Override
    public void execute() {
        System.out.println("Cleared touch and go");
    }
}


public class CommandParser {
    private final JCommander commander;
    private final Map<String, CommandInterface> commands;

    public CommandParser() {
        this.commander = new JCommander();
        this.commands = new HashMap<>();

        CTOCommand cto = new CTOCommand();
        ExitCommand exit = new ExitCommand();
        FullStopCommand fs = new FullStopCommand();
        TGCommand tg = new TGCommand();

        commander.addCommand(cto);
        commander.addCommand(exit);
        commander.addCommand(fs);
        commander.addCommand(tg);

        commands.put("CTO", cto);
        commands.put("EXIT", exit);
        commands.put("FS", fs);
        commands.put("TG", tg);
    }

    public void executeCommand(String commandName, String... args) {
        if (commands.containsKey(commandName)) {
            commander.parse(args);
            commands.get(commandName).execute();
        } else {
            System.out.println("Unknown command: " + commandName);
        }
    }

    // Method to Create and Show Command Window
    public static void createCommandWindow() {
        SwingUtilities.invokeLater(() -> {
            CommandParser parser = new CommandParser();
            JFrame frame = new JFrame("Command Window");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Closes only this window, not the app
            frame.setSize(400, 200);

            JPanel panel = new JPanel();
            JTextField commandInput = new JTextField(20);
            JButton executeButton = new JButton("Execute");
            JTextArea outputArea = new JTextArea(5, 30);
            outputArea.setEditable(false);

            executeButton.addActionListener(e -> {
                String input = commandInput.getText();
                if (!input.isEmpty()) {
                    String[] parts = input.split(" ");
                    String commandName = parts[0];
                    String[] commandArgs = Arrays.copyOfRange(parts, 1, parts.length);
                    parser.executeCommand(commandName, commandArgs);
                    outputArea.append("Executed: " + input + "\n");
                    commandInput.setText("");
                }
            });

            panel.add(commandInput);
            panel.add(executeButton);
            panel.add(new JScrollPane(outputArea));
            frame.add(panel);
            frame.setVisible(true);
        });
    }
}
