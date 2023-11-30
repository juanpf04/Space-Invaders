package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class EnemyWeapon extends Weapon {
	
	public EnemyWeapon(Game game, Position pos, int life, Move dir) {
		super(game, pos, life, dir);
	}
	
	public boolean performAttack(UCMShip other) {
		boolean attack = this.weaponAttack(other);
		
		if(attack)
			this.die();
		
		return attack;
	}
	@Override
	public boolean receiveAttack(UCMWeapon other) { 
		return other.isOnPosition(this.pos);
	}
	
	@Override
	public boolean weaponAttack(GameObject other) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
