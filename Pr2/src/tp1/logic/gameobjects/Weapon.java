package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class Weapon extends GameObject {

	public Weapon(GameWorld game, Position pos, int life, Move dir) {
		super(game, pos, life, dir);
	}

	@Override
	public void automaticMove () {
		performMovement();
		if(isOut())
			this.die();
	}

	protected void die() {
		life = 0;
		onDelete();
	}
	
	@Override
	public boolean performAttack(GameItem other) {
		boolean attacked = this.isAlive() && other.isAlive() && other.isOnPosition(this.pos);
		
		if(attacked) 
			this.weaponAttack(other);
		
		return attacked;
	}

	protected abstract void weaponAttack(GameItem other);
	
	@Override
	public boolean receiveAttack(Burst burst) {
		this.die();
		return true;	
	}
}
