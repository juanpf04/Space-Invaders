package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class UCMWeapon extends Weapon {
	
	public UCMWeapon(Game game, Position pos, int life, Move dir) {
		super(game, pos, life, dir);
	}
	
	@Override
	public boolean receiveAttack(EnemyWeapon other) {
		boolean recieveAttack = other.isOnPosition(this.pos);
	
	if(recieveAttack)
		this.die();
	
	return recieveAttack;
	}
	
	@Override
	public boolean weaponAttack(GameObject other) {
		// TODO Auto-generated method stub
		return false;
	}
}
