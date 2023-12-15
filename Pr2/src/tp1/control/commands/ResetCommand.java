package tp1.control.commands;

import java.io.FileNotFoundException;
import java.io.IOException;

import tp1.control.InitialConfiguration;
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
	public boolean execute(GameModel game) {
		try {
			game.reset(conf);
		} catch (InitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
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
		Command command = null;
		
		if(commandWords.length == 2 && this.matchCommandName(commandWords[0]))
			try {
				command = new ResetCommand(InitialConfiguration.readFromFile(commandWords[1]));
			} catch (FileNotFoundException e) {
				throw new CommandParseException(e.getMessage());
			} catch (IOException e) {
				throw new CommandParseException(e.getMessage());
			}
		else if(commandWords.length == 1 && this.matchCommandName(commandWords[0]))
			command = new ResetCommand(InitialConfiguration.NONE);
			
		return command;
	}

}