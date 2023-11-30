package tp1.logic.gameobjects;

import tp1.logic.GameObjectContainer;
import tp1.logic.Move;
import tp1.logic.Game;

/**
 * 
 * Class that represents the shockWave
 * 
 */
public class ShockWave extends UCMWeapon{

	public static final int DAMAGE = 1;
	private boolean enabled;
	public ShockWave() {
		super(null,null, Game.getRemainingAliens(), Move.NONE);
		this.enabled = false;
	}
	

	public void enable() {
		
		this.enabled = true;
	}
	
	public void disable() {
		
		this.enabled = false;
	}

	public void performAttack(GameObjectContainer container) {
		this.disable();
		
		container.alienReceiveAttack(this);
	}

	@Override
	protected String getSymbol() {return "";}

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
		super.decreaseLife();
	}

}
