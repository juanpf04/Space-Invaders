package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public class Burst extends EnemyWeapon {

	public static final int DAMAGE = 1;
	
	public Burst(GameWorld game, Position pos) {
		super(game, pos, game.containerSize(), Move.NONE);
	}

	@Override
	protected String getSymbol() {
		return "";
	}

	@Override
	protected int getDamage() {
		return DAMAGE;
	}

	@Override
	protected int getArmour() {
		return game.containerSize();
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
