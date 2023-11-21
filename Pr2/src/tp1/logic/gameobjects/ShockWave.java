package tp1.logic.gameobjects;

import tp1.logic.lists.DestroyerAlienList;
import tp1.logic.lists.RegularAlienList;
/**
 * 
 * Class that represents the shockWave
 * 
 */
public class ShockWave extends UCMWeapon{

	public static final int DAMAGE = 1;
	public static final int LIFE = 1;
	
	public ShockWave() {
		super(null,null, LIFE, null);
	}

	public void enable() {
		
		super.life = LIFE;
	}

	public void performAttack(RegularAlienList regularAlienList, DestroyerAlienList destroyerAlienList) {
		super.die();
		
		regularAlienList.allRecieveAttack();
		destroyerAlienList.allRecieveAttack();
	}

	public String stateToString() {
		String state = "OFF";
		
		if(super.isAlive())
			state = "ON";
		
		return state;
	}

	@Override
	protected String getSymbol() {return null;}

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
