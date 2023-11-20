package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class AlienShip extends EnemyShip{

	protected AlienManager alienManager;
	
	public AlienShip(Game game, Position pos, int lives, int damage, Move dir, int points, AlienManager alienManager) {
		super(game, pos, lives, damage, dir, points);
		this.alienManager = alienManager;
	}

}
