package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;
import tp1.logic.gameobjects.DestroyerAlien;

public class Bomb extends EnemyWeapon {

	private DestroyerAlien destroyerAlien;
	
	public Bomb(Position pos, Game game, DestroyerAlien destroyerAlien) {
		super(game, pos, 1, Move.DOWN);
	}
	
	@Override
	public String getSymbol() {
		
		return Messages.BOMB_SYMBOL;
	}

	@Override
	public void onDelete() {
		
		this.destroyerAlien.deleteBomb();
	}

	@Override
	public void automaticMove () {
		if(!this.destroyerAlien.bombEnabled()) {
			super.automaticMove();
		}
	}
	

	/**
	 * Method that implements the attack by the bomb to a ship.
	 * It checks whether both objects are alive and in the same position.
	 * If so call the "actual" attack method {@link weaponAttack}.
	 * @param other the ship possibly under attack
	 * @return <code>true</code> if the ship has been attacked by the bomb.
	 */
	public boolean performAttack(UCMShip other) {
		boolean attack = this.weaponAttack(other);
		
		if(attack)
			this.die();
		
		return attack;
	}
	
	/**
	 * Method that implements the attack by the bomb to a laser.
	 * It checks whether both objects are alive and in the same position.
	 * If so call the "actual" attack method {@link weaponAttack}.
	 * @param other the laser possibly under attack
	 * @return <code>true</code> if the laser has been attacked by the bomb.
	 */
	public boolean performAttack(UCMLaser other) {
		boolean attack = this.weaponAttack(other);
		
		if(attack)
			this.die();
		
		return attack;
	}

}
