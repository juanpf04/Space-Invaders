package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * 
 * Class that represents the laser fired by {@link UCMShip}
 *
 */
public class UCMLaser extends UCMWeapon{
	
	public static final int DAMAGE = 1;
	public static final int LIFE = 1;
	public static final Move DIRECTION = Move.UP;
	
	public UCMLaser(GameWorld game, Position pos) {
		super(game, pos, LIFE, DIRECTION);
	}
	
	@Override
	public String getSymbol() {
		return Messages.LASER_SYMBOL;
	}
	
	@Override
	protected int getDamage() {
		return DAMAGE;
	}

	@Override
	protected int getArmour() {
		return LIFE;
	}

	@Override
	public void onDelete() {
		game.enableLaser();
	}
	
	@Override 
	public boolean performAttack(GameItem other)
	{
		boolean attacked = other.receiveAttack(this); 
		if(attacked) 
			die();
		
		return attacked;
	}
	
}
