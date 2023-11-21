package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class EnemyWeapon extends Weapon {
	
	public EnemyWeapon(Game game, Position pos, int life, Move dir) {
		super(game, pos, life, dir);
	}
	
	
	protected boolean weaponAttack(UCMShip other) {
		
		return other.receiveAttack(this);	
	}
	
	
	protected boolean weaponAttack(UCMWeapon other) {
		
		return other.receiveAttack(this);	
	}
}
