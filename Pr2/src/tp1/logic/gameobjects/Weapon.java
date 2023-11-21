package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class Weapon extends GameObject {
	
	protected Move dir;
	
	public Weapon(Game game, Position pos, int life, Move dir) {
		super(game, pos, life);
		this.dir = dir;
	}

	@Override
	public void automaticMove () {
		performMovement(dir);
		if(isOut())
			die();
	}
}
