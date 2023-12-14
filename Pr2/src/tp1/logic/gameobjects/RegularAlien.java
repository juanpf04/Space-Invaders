package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class RegularAlien extends AlienShip {

	public static final int DAMAGE = 0;
	public static final int LIVES = 2;
	public static final int POINTS = 5;

	public RegularAlien(Position pos, GameWorld game, AlienManager alienManager) {
		super(game, pos, LIVES, Move.LEFT, alienManager);
	}
	
	protected RegularAlien() {}

	@Override
	protected AlienShip copy(GameWorld game, Position pos, AlienManager am){
		return new RegularAlien(pos, game, am);
	}
	
	@Override
	protected String getSymbol() {
		return Messages.REGULAR_ALIEN_SYMBOL;
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
	protected int getPoints() {
		return POINTS;
	}

	@Override
	protected String getDescription() {
		return Messages.REGULAR_ALIEN_DESCRIPTION;
	}
	
	@Override
	public void onDelete() {
		super.onDelete();
		
		if(this.isInBorder()) 
			this.alienManager.disableOnBorder();
			
		this.alienManager.decreaseRemainingAliens();
	}

}