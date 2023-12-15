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
		
		try {
			game.move(move);
			game.update();
			return true;
		} catch (GameModelException e) {
			throw new CommandExecuteException(e.getMessage());
		}
	}


	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		
		if (matchCommandName(commandWords[0])) {
			if(commandWords.length == 1)
				throw new CommandParseException(Messages.COMMAND_PARAMETERS_MISSING);
	 		if(commandWords.length == 2) {
	 			try {
	 				return new MoveCommand(Move.valueOfIgnoreCase(commandWords[1]));
	 			} catch (IllegalArgumentException e) {	 				
	 				throw new CommandParseException(e.getMessage());
	 			}
	 		}	 			
	 		else
	 			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
	 	}
	 	else
	 		return null;	
	}

}