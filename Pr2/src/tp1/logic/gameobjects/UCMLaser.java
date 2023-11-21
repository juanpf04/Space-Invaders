package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * 
 * Class that represents the laser fired by {@link UCMShip}
 *
 */
public class UCMLaser extends UCMWeapon{
	
	protected Move dir;
	
	public UCMLaser(Game game, Position pos) {
		super(game, pos);
		this.dir = Move.UP;
	}
	
	@Override
	public String getSymbol() {
		
		return Messages.LASER_SYMBOL;
	}
	
	/**
	 *  Method called when the laser disappears from the board
	 */
	public void onDelete() {
		
		game.enableLaser();
	}
	
	private void die() {
		
		this.onDelete();
	}

	/**
	 * Checks if the position isn't on board
	 * @return <code>true</code> if the position isn't on board
	 */
	private boolean isOut() {
	
		return !this.pos.posValida();
	}

	/**
	 * Method that implements the attack by the laser to a regular alien.
	 * It checks whether both objects are alive and in the same position.
	 * If so call the "actual" attack method {@link weaponAttack}.
	 * @param other the regular alien possibly under attack
	 * @return <code>true</code> if the alien has been attacked by the laser.
	 */
	public boolean performAttack(RegularAlien other) {
		boolean attack = this.weaponAttack(other);
		
		if(attack)
			this.die();
		
		return attack;
	}

	/**
	 * Method that implements the attack by the laser to a destroyer alien.
	 * It checks whether both objects are alive and in the same position.
	 * If so call the "actual" attack method {@link weaponAttack}.
	 * @param other the destroyer alien possibly under attack
	 * @return <code>true</code> if the alien has been attacked by the laser.
	 */
	public boolean performAttack(DestroyerAlien other) {
		boolean attack = this.weaponAttack(other);
		
		if(attack)
			this.die();
		
		return attack;
	}
	
	/**
	 * Method that implements the attack by the laser to a ufo.
	 * It checks whether both objects are alive and in the same position.
	 * If so call the "actual" attack method {@link weaponAttack}.
	 * @param other the ufo possibly under attack
	 * @return <code>true</code> if the ufo has been attacked by the laser.
	 */
	public boolean performAttack(Ufo other) {
		boolean attack = this.weaponAttack(other);
		
		if(attack)
			this.die();
		
		return attack;
	}

	/**
	 * 
	 * @param other regular alien under attack by the laser
	 * @return <code>true</code>
	 */
	private boolean weaponAttack(RegularAlien other) {
		
		return other.receiveAttack(this);	
	}

	/**
	 * 
	 * @param other destroyer alien under attack by the laser
	 * @return <code>true</code>
	 */
	private boolean weaponAttack(DestroyerAlien other) {
		
		return other.receiveAttack(this);	
	}
	
	/**
	 * 
	 * @param other ufo under attack by the laser
	 * @return <code>true</code>
	 */
	private boolean weaponAttack(Ufo other) {
		
		return other.receiveAttack(this);	
	}
	
	/**
	 * Method to implement the effect of bomb attack on a laser
	 * @param weapon the received bomb
	 * @return <code>true</code> if the attack has been made
	 */
	public boolean receiveAttack(Bomb bomb) {
			boolean recieveAttack = bomb.inPos(this.pos);
		
		if(recieveAttack)
			this.die();
		
		return recieveAttack;
	}

}
