package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class EnemyShip extends Ship{
	
	public EnemyShip(Game game, Position pos, int lives, int damage, Move dir) {
		super(game, pos, lives, damage, dir);
	}
	@Override
	public boolean receiveAttack(UCMWeapon other) { return other.isOnPosition(this.pos);}
}
