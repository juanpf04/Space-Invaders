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
	private boolean enabled;
	
	public ShockWave() {
		super(null,null);
		
		this.enabled = false; 
	}
	
	/**
	 *  Change enabled to true
	 */
	public void enable() {
		
		this.enabled = true;
	}
	
	/**
	 *  Change enable to false
	 */
	public void disable() {
		
		this.enabled = false;
	}

	/**
	 * returns enabled corresponding the shockWave
	 * @return <code>true</code> if enabled is true
	 */
	public boolean isEnabled() {
		
		return this.enabled;
	}

	/**
	 *  Method that implements the attack by the shockWave to all regular aliens and all destroyer aliens.
	 */
	public void performAttack(RegularAlienList regularAlienList, DestroyerAlienList destroyerAlienList) {
		this.disable();
		
		regularAlienList.allRecieveAttack();
		destroyerAlienList.allRecieveAttack();
	}

	/**
	 * returns the String that represents the shockWave status.
	 * @return the String that represents the shockWave status.
	 */
	public String stateToString() {
		String state = "OFF";
		
		if(this.isEnabled())
			state = "ON";
		
		return state;
	}

	@Override
	protected String getSymbol() {return null;}

	@Override
	protected int getDamage() {return DAMAGE;}

	@Override
	protected int getArmour() {return 0;}

	@Override
	public void onDelete() {}

}
