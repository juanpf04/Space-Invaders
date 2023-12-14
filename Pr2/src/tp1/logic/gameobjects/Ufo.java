package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;


public class Ufo extends EnemyShip {

	public static final int DAMAGE = 0;
	public static final int LIVES = 1;
	public static final int POINTS = 25;
	
	private boolean enabled;
	
	public Ufo(GameWorld game) {
		super(game, new Position(Game.DIM_X, 0), LIVES, Move.LEFT);
		this.enabled = false;
	}
	
	protected Ufo() {}

	@Override
	public String getSymbol() {
		return Messages.status(Messages.UFO_SYMBOL, super.getLife());
	}
	
	@Override
	public String getDescription() {
		return Messages.UFO_DESCRIPTION;
	}
	
	@Override
	protected int getDamage() {
		return DAMAGE;
	}

	@Override
	protected int getArmour() {
		return LIVES;
	}

	@Override
	public int getPoints() {
		return POINTS;
	}
	
	@Override
	public void computerAction() {
		if(!this.isEnable() && this.canGenerateRandomUfo()) {
			this.enable();	
		}
	}
	
	@Override
	public void automaticMove () {
		if(this.isEnable()) {
			performMovement();
			if(isOut())
				this.reset();
		}
	}
	
	private final void reset() {
		this.disable();
		this.pos = new Position(Game.DIM_X, 0);
		super.life = LIVES;
	}
	
	private void enable() {
		this.enabled = true;
	}
	
	private void disable() {
		this.enabled = false;
	}
	
	private boolean isEnable() {
		return this.enabled;
	}
	
	@Override
	public void onDelete() {
		super.onDelete();
		this.game.enableShockWave();
		this.reset();
	}
	
	private boolean canGenerateRandomUfo() {
		return game.getRandom().nextDouble() < game.getLevel().getUfoFrequency();
	}

}
