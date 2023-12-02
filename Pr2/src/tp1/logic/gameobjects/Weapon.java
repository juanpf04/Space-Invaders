package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class Weapon extends GameObject {
	
	protected Move dir;
	
	public Weapon(GameWorld game, Position pos, int life, Move dir) {
		super(game, pos, life);
		this.dir = dir;
	}

	@Override
	public void automaticMove () {
		performMovement(dir);
		if(isOut())
			die();
	}
	
	protected void die() {
		
		this.onDelete();
	}
	
	public boolean weaponAttack(GameObject other) {
		boolean attack = other.receiveAttack(this);
		
		if(attack)
			this.die();
		
		return attack;
	}
	
	public boolean performAttack(GameObject other) {
		boolean attack = this.weaponAttack(other);
		
		if(attack)
			this.die();
		
		return attack;
	}
}
