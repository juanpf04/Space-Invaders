package tp1.control.commands;

import tp1.exception.CommandExecuteException;
import tp1.exception.CommandParseException;
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
		
		if(game.move(move)) {
			game.update();
			result = true;
		}
		
		return result;
	}


	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		Command command = null;
		
		if(commandWords.length == 2 && this.matchCommandName(commandWords[0]) 
				&& null!=Move.valueOfIgnoreCase(commandWords[1])) // cambiar a exception
			command = new MoveCommand(Move.valueOfIgnoreCase(commandWords[1]));
		
		return command;
	}

}