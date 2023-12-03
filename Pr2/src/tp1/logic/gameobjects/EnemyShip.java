package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public abstract class EnemyShip extends Ship {
	
	public EnemyShip(GameWorld game, Position pos, int lives, int damage, Move dir) {
		super(game, pos, lives, damage, dir);
	}
	
	protected abstract int getPoints();
	
	@Override
	public String getInfo() {
		return Messages.alienDescription(getDescription(), getPoints(), getDamage(), getArmour());
	}
	@Override
	public boolean receiveAttack(UCMWeapon weapon)
	{
		boolean attacked = weapon.isOnPosition(this.pos);
		if(attacked) life--;
		return attacked;	
	}
}
