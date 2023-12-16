package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class SuperLaser extends UCMLaser {

	public static final int DAMAGE = 2;
	
	public SuperLaser(GameWorld game, Position pos) {
		super(game, pos);
	}

	@Override
	public String getSymbol() {
		return Messages.SUPERLASER_SYMBOL;
	}
	
	@Override
	protected int getDamage() {
		return DAMAGE;
	}
	
}
