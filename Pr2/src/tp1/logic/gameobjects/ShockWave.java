package tp1.logic.gameobjects;

import tp1.logic.GameObjectContainer;
import tp1.logic.GameWorld;
import tp1.logic.Move;

public class ShockWave extends UCMWeapon{

	public static final int DAMAGE = 1;

	public ShockWave(GameWorld game) {
		super(game,null, game.getRemainingAliens(), Move.NONE);
	}

	@Override
	protected String getSymbol() {return "";}

	@Override
	protected int getDamage() {
		return DAMAGE;
	}

	@Override
	protected int getArmour() {
		return game.getRemainingAliens();
	}

	@Override
	public void onDelete() {
		super.decreaseLife();
	}

	@Override
	public void automaticMove() {}
}
