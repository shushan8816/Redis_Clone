package server.commands;

public class Command {

    private static CommandPattern commandPattern;


    public Command(CommandPattern commandPattern) {

        this.commandPattern = commandPattern;
    }

    public void setCommandPattern(CommandPattern commandPattern) {

        this.commandPattern = commandPattern;
    }

    public String execCommand(String[] commands) {

        return commandPattern.execute(commands);
    }
}
