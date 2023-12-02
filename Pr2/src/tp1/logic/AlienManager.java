package tp1.logic;

import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.gameobjects.Ufo;
import tp1.logic.GameObjectContainer;
import tp1.logic.GameWorld;

/**
 * 
 * Manages alien initialization and
 * used by aliens to coordinate movement
 *
 */
public class AlienManager {
	
	private Level level;
	private GameWorld game;
	private int remainingAliens;
	
	private boolean squadInFinalRow;
	private int shipsOnBorder;
	private boolean onBorder;

	public AlienManager(GameWorld game) {
		this.game = game;
		this.level = this.game.getLevel();
		this.onBorder = false;
		this.shipsOnBorder = 0;
		this.squadInFinalRow = false;
	}
		
	// INITIALIZER METHODS
	
	public  GameObjectContainer initialize() {
		this.remainingAliens = 0;
		GameObjectContainer container = new GameObjectContainer();
		
		initializeRegularAliens(container);
		initializeDestroyerAliens(container);
		initializeUfo(container);
		
		return container;
	}
	
	private void initializeUfo(GameObjectContainer container) {
		container.add(new Ufo(game));
	}
	
	private void initializeRegularAliens (GameObjectContainer container) {

		for(int i = 0; i < this.level.getNumRegularAliens()/this.level.getNumRowsRegularAliens(); i++) {
			for(int j = 0; j < this.level.getNumRowsRegularAliens(); j++) {
				container.add(new RegularAlien(new Position(2 + i, 1 + j) , this.game, this));
				this.remainingAliens++;
			}
		}
	}
	
	private void initializeDestroyerAliens(GameObjectContainer container) {
		int a = 4 / this.level.getNumDestroyerAliens();
		
		for(int i = 0; i < this.level.getNumDestroyerAliens(); i++) {		
			container.add(new DestroyerAlien(new Position(a + 1 + i, 1 + this.level.getNumRowsRegularAliens()) , this.game, this));
			this.remainingAliens++;
		}
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
