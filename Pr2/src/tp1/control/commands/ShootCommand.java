package tp1.control.commands;

import tp1.exception.CommandExecuteException;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class ShootCommand extends NoParamsCommand {
		  		
	@Override
	public boolean execute(GameModel game) throws CommandExecuteException {
		boolean result = false;
		
		if(game.shootLaser()) {
			game.update();
			result = true;
		}
		
		return result;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_SHOOT_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_SHOOT_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_SHOOT_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_SHOOT_HELP;
	}

}