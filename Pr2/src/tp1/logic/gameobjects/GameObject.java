package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;

public abstract class GameObject {

	protected Position pos;
	protected Game game;
	
	public GameObject(Game game, Position pos) {
		
		this.game = game;
		this.pos = pos;
	}

	
	
}
