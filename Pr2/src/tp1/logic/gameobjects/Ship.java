package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class Ship extends GameObject{
	
	public Ship(GameWorld game, Position pos, int lives, Move dir) {
		super(game, pos,  lives, dir);
	}
	
	public Ship() {}
	
	public abstract String getInfo();
	protected abstract String getDescription();

	protected void loseLife(int damage) {
		this.life -= damage;
	}
		
	@Override
	public boolean receiveAttack(Burst burst) {
		this.loseLife(burst.getDamage());
		
		if(!isAlive())
				onDelete();
		
		return true;	
	}
	
}
