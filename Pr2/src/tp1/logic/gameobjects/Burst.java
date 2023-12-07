package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public class Burst extends EnemyWeapon{

	public Burst(GameWorld game, Position pos) {
		super(game, pos, game.containerSize(), Move.NONE);
	}

	@Override
	protected String getSymbol() {
		return "";
	}

	@Override
	protected int getDamage() {
		return 1;
	}

	@Override
	protected int getArmour() {
		return 8;
	}

	@Override
	public void onDelete() {}

	@Override
	public void automaticMove() {}
	
	@Override 
	public boolean performAttack(GameItem other) {
		boolean attacked = this.isAlive() && other.isAdjacent(this.pos); 
		
		if(attacked) 
			other.receiveAttack(this);
	
		life--;
		
		return attacked;
	}
	
}
