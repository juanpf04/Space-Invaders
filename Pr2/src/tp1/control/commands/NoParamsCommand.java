package tp1.control.commands;

public abstract class NoParamsCommand extends Command {

	@Override
	public Command parse(String[] commandWords) {
		Command command = null;
		
		if(commandWords.length == 1 && this.matchCommandName(commandWords[0]))
			command = this;
		
		return command;
	}
	
}