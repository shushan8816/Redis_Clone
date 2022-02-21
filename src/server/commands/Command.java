package server.commands;

import server.Exeptions.BadRequestException;

public class Command {

    private static CommandPattern commandPattern;


    public Command(CommandPattern commandPattern) {

        Command.commandPattern = commandPattern;
    }

    public String execCommand(String[] commands) throws BadRequestException {

        return commandPattern.execute(commands);
    }
}
