package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;


public class Ufo extends EnemyShip{

	public static final int DAMAGE = 0;
	public static final int LIVES = 1;
	public static final int POINTS = 25;
	
	private boolean enabled;
	
	public Ufo(Game game) {
		super(game, new Position(Game.DIM_X, 0), LIVES, DAMAGE, Move.LEFT, POINTS);
		this.enabled = false;
	}
	

	/**
	 * returns the String that represents the ufo symbol.
	 * @return the String that represents the ufo symbol.
	 */
	public String symbol() {
		
		return Messages.status(Messages.UFO_SYMBOL, this.lives);
	}
	
	
	public void computerAction() {
		if(!enabled && canGenerateRandomUfo()) {
			enable();	
		}
	}
	
	/**
	 *  Implements the automatic movement of the ufo
	 */
	public void automaticMove () {
		if(this.isEnable()) {
			performMovement(dir);
			if(isOut())
				onDelete();
		}
	}
	
	
	private void enable() {
		
		this.enabled = true;
	}

	/**
	 * Checks if the position isn't on board
	 * @return <code>true</code> if the position isn't on board
	 */
	private boolean isOut() {
	
		return !this.pos.posValida();
	}

	/**
	 * Methods that updates the position of the ufo
	 * @param move the move that wants to make
	 */
	private void performMovement(Move dir) {
		
		this.pos = this.pos.newPos(dir);
	}

	
	public void onDelete() {
		
		this.enabled = false;
		this.pos = new Position(Game.DIM_X, 0);
		this.lives = LIVES;
	}

	/**
	 * Checks if the game should generate an ufo.
	 * 
	 * @return <code>true</code> if an ufo should be generated.
	 */
	private boolean canGenerateRandomUfo(){
		
		return game.getRandom().nextDouble() < game.getLevel().getUfoFrequency();
	}

	
	public boolean isEnable() {
	
		return this.enabled;
	}

	
	public void checkAttack(UCMLaser ucmLaser) {
		
		if(ucmLaser.performAttack(this)) {
			this.decreaseLive();
		}
	}
	
	
	public boolean receiveAttack(UCMLaser ucmLaser) {
		
		return ucmLaser.inPos(this.pos);
	}

	/**
	 * Checks if the position is the same as the position given
	 * @param pos the position received
	 * @return <code>true</code> if the position is the same as the position given
	 */
	public boolean inPos(Position pos) {
		
		return this.pos.equals(pos);
	}
	

	public boolean isAlive() {
		
		return this.lives > 0;
	}

	
	public void decreaseLive() {
		
		this.lives--;
		if(!this.isAlive()) {
			this.onDelete();
			this.game.enableShockWave();
			this.game.addPoints(POINTS);
		}
	}

}
