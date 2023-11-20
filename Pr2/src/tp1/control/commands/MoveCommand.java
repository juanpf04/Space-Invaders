package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.logic.Game;
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
	public ExecutionResult execute(Game game) {
		ExecutionResult result = new ExecutionResult(Messages.MOVEMENT_ERROR);

		// comprobar si es un movimiento valido
		
		if(game.move(move)) {
			game.update();
			result = new ExecutionResult(true);
		}
		
		return result;
	}


	@Override
	public Command parse(String[] commandWords) {
		Command command = null;
		
		if(commandWords.length == 2 && this.matchCommandName(commandWords[0])) {
			command = this;
			this.move = Move.valueOf(commandWords[1].toUpperCase());
		}
		
		return command;
	}

}