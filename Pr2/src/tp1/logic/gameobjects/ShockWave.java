package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public class ShockWave extends UCMWeapon{

	public static final int DAMAGE = 1;

	public ShockWave(GameWorld game) {
		super(game,new Position(-1,-1), game.getRemainingAliens(), Move.NONE);
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
		return game.getRemainingAliens();
	}

	@Override
	public void onDelete() {}

	@Override
	public void automaticMove() {}
	
}
