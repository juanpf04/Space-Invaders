package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
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

	public RegularAlien( Position pos, GameWorld game, AlienManager alienManager) {
		super(game, pos, LIVES, DAMAGE, Move.LEFT, alienManager);
	}
	
	@Override
	public String getSymbol() {
		return Messages.status(Messages.REGULAR_ALIEN_SYMBOL, getLife());
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
	public int getPoints() {
		return POINTS;
	}

	@Override
	public String getDescription() {
		return Messages.REGULAR_ALIEN_DESCRIPTION;
	}
	
	@Override
	public void onDelete() {
		super.onDelete();
		
		if(this.isInBorder()) 
			this.alienManager.disableOnBorder();
			
		this.alienManager.decreaseRemainingAliens();
		this.game.receivePoints(getPoints());	
	}

}