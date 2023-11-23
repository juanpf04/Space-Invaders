package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class UCMWeapon extends Weapon {
	
	public UCMWeapon(Game game, Position pos, int life, Move dir) {
		super(game, pos, life, dir);
	}
	
	public boolean performAttack(EnemyShip other) {
		boolean attack = other.receiveAttack(this);
		
		if(attack)
			this.die();
		
		return attack;
	}
	public boolean performAttack(EnemyWeapon other)
	{
		boolean attack = other.receiveAttack(this);
		if(attack)
			this.die();
		return attack;
	}
	
	public boolean receiveAttack(EnemyWeapon other) {
		boolean recieveAttack = other.isOnPosition(this.pos);
	
	if(recieveAttack)
		this.die();
	
	return recieveAttack;
}
}
