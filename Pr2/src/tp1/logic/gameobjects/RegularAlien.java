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

	public RegularAlien( Position pos, Game game, AlienManager alienManager) {
		super(game, pos, LIVES, DAMAGE, Move.LEFT, alienManager);
	}
	
	@Override
	public String getSymbol() {
		
		return Messages.status(Messages.REGULAR_ALIEN_SYMBOL, this.life);
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