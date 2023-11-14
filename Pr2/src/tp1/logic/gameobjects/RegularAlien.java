package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * 
 * Class representing a regular alien
 *
 */
public class RegularAlien extends AlienShip{

	public static final int DAMAGE = 0;
	public static final int LIVES = 2;
	public static final int POINTS = 5;

	private int cyclesToMove;
	private int speed;


	public RegularAlien( Position pos, Game game, AlienManager alienManager) {
		super(game, pos, LIVES, DAMAGE, Move.LEFT, POINTS, alienManager);
		this.speed = game.getLevel().getNumCyclesToMoveOneCell();
		this.cyclesToMove = this.speed;
	}
	
	/**
	 * returns the String that represents the regular alien symbol.
	 * @return the String that represents the regular alien symbol.
	 */
	public String symbol() {
		
		return Messages.status(Messages.REGULAR_ALIEN_SYMBOL, this.lives);
	}
	
	/**
	 *  Implements the automatic movement of the regular alien	
	 */
	public void automaticMove() {
		
		if(this.cyclesToMove == 0)
		{
			this.performMovement(this.dir);
			this.cyclesToMove = this.speed; 
			if(this.isInBorder())
				this.alienManager.shipOnBorder();
				
		}
		else if(this.alienManager.onBorder())
			this.descent();
		
		else
			this.cyclesToMove--;
	}

	
	private void descent() {
		
		this.performMovement(Move.DOWN);
		this.dir = this.dir.flip();
		this.game.checkLaserAttack(this);
		if(this.isAlive())
			this.alienManager.decreaseOnBorder();
		if(this.isInFinalRow())
			this.alienManager.isInFinalRow();
	}

	
	private boolean isInFinalRow() {

		return this.pos.inFinalRow();
	}

	/**
	 * Methods that updates the position of the regular alien
	 * @param move the move that wants to make
	 */
	private void performMovement(Move dir) {
		
		this.pos = this.pos.newPos(dir);
	}

	
	private boolean isInBorder() {
		
		return this.pos.isInBorder();
	}

	
	public boolean receiveAttack(UCMLaser laser) {
		boolean recieveAttack = laser.inPos(this.pos);
		
		if(recieveAttack)
			this.decreaseLive();
		
		return recieveAttack;
	}

	/**
	 * Checks if the position is the same as the position given
	 * @param pos the position received
	 * @return <code>true</code> if the position is the same as the position given
	 */
	public boolean inPos(Position pos) {
		
		return this.pos.equals(pos);
	}
	
	/**
	 * Checks if 
	 * @return <code>true</code> if 
	 */
	public boolean isAlive() {
		
		return this.lives > 0;
	}

	
	public void decreaseLive() {
		
		this.lives--;
		if(!this.isAlive()) {
			if(this.isInBorder()) 
				this.alienManager.disableOnBorder();
				
			this.alienManager.decreaseRemainingAliens();
			this.game.addPoints(POINTS);
		}
	}

}