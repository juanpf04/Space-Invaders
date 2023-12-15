package tp1.control.commands;

import tp1.exception.CommandExecuteException;
import tp1.exception.CommandParseException;
import tp1.exception.GameModelException;
import tp1.exception.NotAllowedMoveException;
import tp1.exception.OffWorldException;
import tp1.logic.GameModel;
import tp1.logic.Move;
import tp1.view.Messages;

public class MoveCommand extends Command {

	private Move move;

	public MoveCommand() {}

	protected MoveCommand(Move move) {
		this.move = move;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_MOVE_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_MOVE_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_MOVE_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_MOVE_HELP;
	}

	@Override
	public boolean execute(GameModel game) throws CommandExecuteException {
		boolean result = false;
		
		try {
			game.move(move);
			game.update();
			result = true;
		} catch (GameModelException e) {
			e.printStackTrace();
			
		}
		
		return result;
	}


	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		
		if (matchCommandName(commandWords[0])) {
			if(commandWords.length == 1)
				throw new CommandParseException(Messages.COMMAND_PARAMETERS_MISSING);
	 		if(commandWords.length == 2) 
	 			return new MoveCommand(Move.valueOfIgnoreCase(commandWords[1]));
	 		else
	 			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
	 	}
	 	else
	 		return null;	
	}

}