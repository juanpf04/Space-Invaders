package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public abstract class EnemyShip extends Ship {
	
	public EnemyShip(GameWorld game, Position pos, int lives, Move dir) {
		super(game, pos, lives, dir);
	}
	
	public EnemyShip() {}
	
	protected abstract int getPoints();
	
	@Override
	public String getInfo() {
		return Messages.alienDescription(getDescription(), getPoints(), getDamage(), getArmour());
	}
	
	@Override
	public boolean receiveAttack(UCMWeapon weapon) {
		this.loseLife(weapon.getDamage());
		
		if(!isAlive())
				onDelete();
		
		return true;	
	}
	
	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {
		this.loseLife(weapon.getDamage());
		
		if(!isAlive())
				onDelete();
		
		return true;	
	}
	
	@Override
	public void onDelete() {
		this.game.receivePoints(getPoints());
	}
	
}
