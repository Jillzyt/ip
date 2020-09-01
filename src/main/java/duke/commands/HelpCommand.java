package src.main.java.duke.commands;

/**
 * Help function that helps the user to clarify doubts
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        String[] stringArray = {src.main.java.duke.commands.AddTodoCommand.MESSAGE_USAGE
                , src.main.java.duke.commands.AddEventCommand.MESSAGE_USAGE
                , src.main.java.duke.commands.AddDeadlineCommand.MESSAGE_USAGE
                , src.main.java.duke.commands.DeleteCommand.MESSAGE_USAGE
                , src.main.java.duke.commands.MarkDoneCommand.MESSAGE_USAGE
                ,HelpCommand.MESSAGE_USAGE
                , src.main.java.duke.commands.ExitCommand.MESSAGE_USAGE};

        String resultString = "";

        // Loop through the string array
        for (int i = 0; i < stringArray.length; i++ ) {
            resultString += "\n" +  stringArray[i] + "\n";
            ;
        }

        return new src.main.java.duke.commands.CommandResult(resultString);
    }
}
