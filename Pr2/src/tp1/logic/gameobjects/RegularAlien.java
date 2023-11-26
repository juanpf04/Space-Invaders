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
	
	public void decreaseLife() {
		
		super.decreaseLife();
		if(!this.isAlive()) {
			if(this.isInBorder()) 
				this.alienManager.disableOnBorder();
				
			this.alienManager.decreaseRemainingAliens();
			this.game.receivePoints(POINTS);
		}
	}

	@Override
	protected int getDamage() {
		return DAMAGE;
	}

	@Override
	protected int getArmour() {
		return LIVES;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}