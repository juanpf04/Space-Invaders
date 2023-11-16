package tp1.control.commands;

import java.util.Arrays;
import java.util.List;

import tp1.view.Messages;

public class CommandGenerator {

	private static final List<Command> availableCommands = Arrays.asList(
		new HelpCommand(),
		new MoveCommand(),
		new ExitCommand(),
		new NoneCommand()
		//TODO fill with your code
	);

	public static Command parse(String[] commandWords) {		
		Command command = null;
		
		for (Command c: availableCommands) {
			
			if(command == null)
				command = c.parse(commandWords);
		}
		
		return command;
	}
		
	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();	
		
		for (Command c: availableCommands) {
			
			commands.append(c.getHelp()).append(Messages.LINE_SEPARATOR);
		}
		
		return commands.toString();
	}

}