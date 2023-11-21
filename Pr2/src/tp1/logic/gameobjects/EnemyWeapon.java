package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class EnemyWeapon extends Weapon {
	
	public EnemyWeapon(Game game, Position pos, int life, Move dir) {
		super(game, pos, life, dir);
	}
	
	public boolean performAttack(UCMShip other) {
		boolean attack = other.receiveAttack(this);
		
		if(attack)
			this.die();
		
		return attack;
	}
	
	public boolean performAttack(UCMWeapon other) {
		boolean attack = other.receiveAttack(this);
		
		if(attack)
			this.die();
		
		return attack;
	}
	
}
