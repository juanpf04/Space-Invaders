package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExplosiveAlien extends RegularAlien {
	
	public static final int POINTS = 12;

	public ExplosiveAlien(Position pos, GameWorld game, AlienManager alienManager) {
		super(pos, game, alienManager);
	}
	
	public ExplosiveAlien() {}

	@Override
	protected AlienShip copy(GameWorld game, Position pos, AlienManager am) {
		return new ExplosiveAlien(pos, game, am);
	}

	@Override
	protected int getPoints() {
		return POINTS;
	}

	@Override
	protected String getDescription() {
		return Messages.EXPLOSIVE_ALIEN_DESCRIPTION;
	}

	@Override
	protected String getSymbol() {
		return Messages.EXPLOSIVE_ALIEN_SYMBOL;
	}
	
	@Override
	public void onDelete() {
		super.onDelete();
		//game.addObject(new Burst(this.pos, this.game));
	}

}
