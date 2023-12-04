package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class EnemyWeapon extends Weapon {
	
	public EnemyWeapon(GameWorld game, Position pos, int life, Move dir) {
		super(game, pos, life, dir);
	}
	
	
	@Override
	public boolean performAttack(GameItem other)
	{
		boolean attacked = other.receiveAttack(this);
		if(attacked) {
			life = 0;
			if(!isAlive())
				onDelete();
		}
		return attacked;
	}
	
	@Override
	public boolean receiveAttack(UCMWeapon weapon)
	{
		boolean attacked = weapon.isOnPosition(this.pos);
		if(attacked) {
			life -= weapon.getDamage();
			if(!isAlive())
				onDelete();
		}
		return attacked;
	}
	
	
}
