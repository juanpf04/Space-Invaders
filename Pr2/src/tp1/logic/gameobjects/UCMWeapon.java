package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class UCMWeapon extends Weapon {
	
	public UCMWeapon(GameWorld game, Position pos, int life, Move dir) {
		super(game, pos, life, dir);
	}
	
	@Override
	protected void weaponAttack(GameItem other) {
		if(other.receiveAttack(this))
				die();
	}
	
	@Override
	public boolean receiveAttack(EnemyWeapon other) {
		die();
		return true;
	}
}
