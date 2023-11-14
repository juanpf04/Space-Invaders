package tp1.control;

import static tp1.view.Messages.debug;

import java.util.Scanner;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.view.GamePrinter;
import tp1.view.Messages;

/**
 *  Accepts user input and coordinates the game execution logic
 */
public class Controller {

	private Game game;
	private Scanner scanner;
	private GamePrinter printer;

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		printer = new GamePrinter(game);
	}

	/**
	 * Show prompt and request command.
	 *
	 * @return the player command as words
	 */
	private String[] prompt() {
		System.out.print(Messages.PROMPT);
		String line = scanner.nextLine();
		String[] words = line.toLowerCase().trim().split("\\s+");

		System.out.println(debug(line)); 

		return words;
	}

	/**
	 * Runs the game logic
	 */
	public void run() {
		boolean exit = false;
		
		this.printer.printGame();
		
		while(!exit && !this.game.playerWin() && !this.game.aliensWin()) {
					
			String[] command = prompt();
			
	        switch (command[0].toLowerCase()) {
	        case "move":
	        case "m":
	        	if(command.length == 2)
	        		this.executeMove(command[1].toLowerCase());
	        	else 
	        		this.printer.incorrectParameterNumber();
	            break;
	        case "shoot":
	        case "s":
	        	if(this.game.shootLaser()) {
	        		this.game.update();	
	        		this.printer.printGame();
	        	}
	        	else
	        		this.printer.laserError();
	        	break;
	        case "shockwave":
	        case "w":
	        	if(this.game.shootShockWave())  {
	        		this.game.update();	
	        		this.printer.printGame();
	        	}     		
	        	else
	        		this.printer.shockWaveError();
	        	break;
	        case "list":
	        case "l":
	        	this.printer.list();
	        	break;
	        case "reset":
	        case "r":
	        	this.game.reset();
	        	this.printer.printGame();
	        	break;
	        case "help":
	        case "h":
	        	this.printer.help();
	        	break;
	        case "exit":
	        case "e":
	        	exit = true;
	        	break;
	        case "none":
	        case "n":
	        	this.game.update();
	        	this.printer.printGame();
	        	break;
	        default:
	        	if(command[0].length() == 0) {
	        		this.game.update();	
	        		this.printer.printGame();
	        	}
	        	else
	        		this.printer.unknownCommand();
	        	break;
	        }
		}
		
		this.printer.printEndMessage();
	}
	
	
	private void executeMove(String direction) {
		
		switch(direction) {
		case "left":
		case "right":
		case "lleft":
		case "rright":
		case "up":
		case "down":
		case "none":
			Move move = Move.valueOf(direction.toUpperCase());
			if(this.game.moveUCMShip(move)) {
        		this.game.update();	
        		this.printer.printGame();
        	}
			else
				this.printer.movementError();		
			break;
		default:			
			this.printer.directionError(direction);
			break;
		}
	}	
}
