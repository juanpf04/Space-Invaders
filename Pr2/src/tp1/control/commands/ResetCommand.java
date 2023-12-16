package tp1.control.commands;

import java.io.IOException;

import tp1.control.InitialConfiguration;
import tp1.exception.CommandExecuteException;
import tp1.exception.CommandParseException;
import tp1.exception.InitializationException;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class ResetCommand extends Command {
  	
	private InitialConfiguration conf;
	
	public ResetCommand() {}
	
	protected ResetCommand(InitialConfiguration conf) {
		this.conf = conf;
	}
	
	@Override
	public boolean execute(GameModel game) throws CommandExecuteException {
		try {
			game.reset(conf);
			return true;
		} catch (InitializationException e) {
			throw new CommandExecuteException(Messages.INITIAL_CONFIGURATION_ERROR, e);
		}
	}
	
	@Override
	protected String getName() {
		return Messages.COMMAND_RESET_NAME;
	}
	
	@Override
	protected String getShortcut() {
		return Messages.COMMAND_RESET_SHORTCUT;
	}
	
	@Override
	protected String getDetails() {
		return Messages.COMMAND_RESET_DETAILS;
	}
	
	@Override
	protected String getHelp() {
		return Messages.COMMAND_RESET_HELP;
	}
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		
		if (matchCommandName(commandWords[0])) {
			if(commandWords.length == 1)
				return new ResetCommand(InitialConfiguration.NONE);
	 		if(commandWords.length == 2)
				try {
					return new ResetCommand(InitialConfiguration.readFromFile(commandWords[1]));
				} catch (IOException e) {
					throw new CommandParseException(e.getMessage());
				}
			else
	 			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
	 	}
	 	else
	 		return null;	
	}

}