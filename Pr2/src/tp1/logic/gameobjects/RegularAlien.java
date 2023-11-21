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
	
	@Override
	public String getSymbol() {
		
		return Messages.status(Messages.REGULAR_ALIEN_SYMBOL, this.life);
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
	
	private boolean isInBorder() {
		
		return this.pos.isInBorder();
	}

	
	public boolean receiveAttack(UCMLaser laser) {
		boolean recieveAttack = laser.isOnPosition(this.pos);
		
		if(recieveAttack)
			this.decreaseLive();
		
		return recieveAttack;
	}
	
	public void decreaseLive() {
		
		this.life--;
		if(!this.isAlive()) {
			if(this.isInBorder()) 
				this.alienManager.disableOnBorder();
				
			this.alienManager.decreaseRemainingAliens();
			this.game.addPoints(POINTS);
		}
	}

}