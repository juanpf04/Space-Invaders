package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.control.InitialConfiguration;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class ResetCommand extends Command{
		  	
		private InitialConfiguration conf;
		
		public ResetCommand() {}

		protected ResetCommand(InitialConfiguration conf) {
			this.conf = conf;
		}
	
		@Override
		public ExecutionResult execute(GameModel game) {
			game.reset(conf);
			return new ExecutionResult(true);
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
		public Command parse(String[] commandWords) {
			Command command = null;
			
			if(commandWords.length == 2 && this.matchCommandName(commandWords[0])) {
				
				if(null != InitialConfiguration.valueOfIgnoreCase(commandWords[1])) // cambiar por excepcion
					command = new ResetCommand(InitialConfiguration.valueOfIgnoreCase(commandWords[1]));
			}
			else if(commandWords.length == 1 && this.matchCommandName(commandWords[0]))
				command = new ResetCommand(InitialConfiguration.NONE);
				
			return command;
		}

	}