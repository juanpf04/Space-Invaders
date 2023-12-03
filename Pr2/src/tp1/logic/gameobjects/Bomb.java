package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;
import tp1.logic.gameobjects.DestroyerAlien;

public class Bomb extends EnemyWeapon {

	public static final int DAMAGE = 1;
	public static final int LIFE = 1;
	
	private DestroyerAlien destroyerAlien;
	
	public Bomb(Position pos, GameWorld game, DestroyerAlien destroyerAlien) {
		super(game, pos, 1, Move.DOWN);
		this.destroyerAlien = destroyerAlien;
	}
	
	@Override
	public String getSymbol() {
		
		return Messages.BOMB_SYMBOL;
	}

	@Override
	public void onDelete() {
		
		this.destroyerAlien.deleteBomb();
	}

	@Override
	public void automaticMove () {
		if(!this.destroyerAlien.bombEnabled()) {
			super.automaticMove();
		}
	}

	@Override
	protected int getDamage() {
		return DAMAGE;
	}

	@Override
	protected int getArmour() {
		return LIFE;
	}
}
