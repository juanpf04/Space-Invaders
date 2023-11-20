package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class Ship extends GameObject{
	
	protected int damage;
	protected Move dir;
	
	public Ship(Game game, Position pos, int lives, int damage, Move dir) {
		super(game, pos,  lives);
		this.damage = damage;
		this.dir = dir;
	}

	
	
}
