package tp1.logic;

import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.lists.DestroyerAlienList;
import tp1.logic.lists.RegularAlienList;
import tp1.logic.Game;

/**
 * 
 * Manages alien initialization and
 * used by aliens to coordinate movement
 *
 */
public class AlienManager {
	
	private Level level;
	private Game game;
	private int remainingAliens;
	
	private boolean squadInFinalRow;
	private int shipsOnBorder;
	private boolean onBorder;

	public AlienManager(Game game) {
		this.game = game;
		this.level = this.game.getLevel();
		this.remainingAliens = this.level.getNumRegularAliens() + this.level.getNumDestroyerAliens();
		this.onBorder = false;
		this.shipsOnBorder = 0;
		this.squadInFinalRow = false;
	}
		
	// INITIALIZER METHODS
	
	/**
	 * Initializes the list of regular aliens
	 * @return the initial list of regular aliens according to the current level
	 */
	protected RegularAlienList initializeRegularAliens() {
		RegularAlienList regularAlienList = new RegularAlienList(this.level);
		
		for(int i = 0; i < this.level.getNumRegularAliens()/this.level.getNumRowsRegularAliens(); i++) {
			for(int j = 0; j < this.level.getNumRowsRegularAliens(); j++) {
				regularAlienList.addObject(new RegularAlien(new Position(2 + i, 1 + j) , this.game, this));
			}
		}
		
		return regularAlienList;
	}

	/**
	 * Initializes the list of destroyer aliens
	 * @return the initial list of destroyer aliens according to the current level
	 */
	protected  DestroyerAlienList initializeDestroyerAliens() {
		DestroyerAlienList destroyerAlienList = new DestroyerAlienList(this.level);
		int a = 4 / this.level.getNumDestroyerAliens();
		
		for(int i = 0; i < this.level.getNumDestroyerAliens(); i++) {
				
			destroyerAlienList.addObject(new DestroyerAlien(new Position(a + 1 + i, 1 + this.level.getNumRowsRegularAliens()) , this.game, this));
		}
		
		return destroyerAlienList;
	}

	
	// CONTROL METHODS
		
	public void shipOnBorder() {
		if(!this.onBorder) {
			this.onBorder = true;
			this.shipsOnBorder = this.remainingAliens;
		}
	}

	public boolean onBorder() {
	
		return this.onBorder;
	}

	public boolean squadInFinalRow() {
		
		return this.squadInFinalRow;
	}
	
	public void isInFinalRow() {
		
		this.squadInFinalRow = true;
	}
	
	public void decreaseOnBorder() {
		
		this.shipsOnBorder--;
		if(this.shipsOnBorder == 0)
			this.onBorder = false;
	}

	public int getRemainingAliens() {
		
		return this.remainingAliens;
	}

	public void decreaseRemainingAliens() {
		
		this.remainingAliens--;
		if(this.onBorder())
			this.decreaseOnBorder();
	}

	public void disableOnBorder() {
		
		this.onBorder = false;
	}

}
