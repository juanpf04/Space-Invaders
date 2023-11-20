package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class EnemyShip extends Ship{

	protected int points;
	
	public EnemyShip(Game game, Position pos, int lives, int damage, Move dir, int points) {
		super(game, pos, lives, damage, dir);
		this.points = points;
	}

	
}
