package server.commands;

import java.util.Locale;

public class Parser {
    public final String command;

    public Parser(String command) {

        this.command = command;
    }

    public String parseCommand() {
        String[] newCommand = command.split(" ");
        Command command = new Command(CommandPattern.valueOf(newCommand[0].toUpperCase(Locale.ROOT)));
        return command.execCommand(newCommand);
    }
}
