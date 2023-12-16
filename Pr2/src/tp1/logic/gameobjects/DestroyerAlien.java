package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class DestroyerAlien extends AlienShip {
	
	public static final int DAMAGE = 1;
	public static final int LIVES = 1;
	public static final int POINTS = 10;
	
	private boolean bombEnabled;

	public DestroyerAlien(Position pos, GameWorld game, AlienManager alienManager) {
		
		super(game, pos, LIVES, Move.LEFT, alienManager);
		this.bombEnabled = true;
	}
	
	protected DestroyerAlien() {}
	
	@Override
	protected AlienShip copy(GameWorld game, Position pos, AlienManager am) {
		return new DestroyerAlien(pos, game, am);
	}

	@Override
	protected String getSymbol() {
		return Messages.DESTROYER_ALIEN_SYMBOL;
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
	protected String getDescription() {
		return Messages.DESTROYER_ALIEN_DESCRIPTION;
	}
	
	@Override
	public void onDelete() {
		super.onDelete();	
		this.alienManager.decreaseRemainingAliens();
	}

	public boolean bombEnabled() {
		return this.bombEnabled;
	}

	public void deleteBomb() {
		this.bombEnabled = true;
	}

	@Override
	public void computerAction() {
		if(this.bombEnabled() && this.canGenerateRandomShoot() && !this.alienManager.onBorder()) {
			
			this.game.addObject(new Bomb(this.pos, this.game, this));
			this.bombEnabled = false; 
		}
	}
	
	private boolean canGenerateRandomShoot() {
		return game.getRandom().nextDouble() < game.getLevel().getShootFrequency();
	}
	
}
