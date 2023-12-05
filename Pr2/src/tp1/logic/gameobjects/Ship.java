package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class Ship extends GameObject{
	
	protected Move dir;
	
	public Ship(GameWorld game, Position pos, int lives, Move dir) {
		super(game, pos,  lives);
		this.dir = dir;
	}

	public abstract String getInfo();
	public abstract String getDescription();

	protected void loseLife(int damage) {
		this.life -= damage;
	}
		
}
