package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class SuperLaserCommand extends NoParamsCommand {
		
	@Override
	public ExecutionResult execute(GameModel game) {
		ExecutionResult result = new ExecutionResult(Messages.SUPERLASER_ERROR); 
		
		if(game.shootSuperLaser()) {
			game.update();
			result = new ExecutionResult(true);
		}
		
		return result;
	}
	
	@Override
	protected String getName() {
		return Messages.COMMAND_SUPERLASER_NAME;
	}
	
	@Override
	protected String getShortcut() {
		return Messages.COMMAND_SUPERLASER_SHORTCUT;
	}
	
	@Override
	protected String getDetails() {
		return Messages.COMMAND_SUPERLASER_DETAILS;
	}
	
	@Override
	protected String getHelp() {
		return Messages.COMMAND_SUPERLASER_HELP;
	}

}
