package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class GameObject implements GameItem {

	protected Position pos;
	protected int life;
	protected GameWorld game;
	
	public GameObject(GameWorld game, Position pos, int life) {	
		this.pos = pos;
		this.game = game;
		this.life = life;
	}
	
	@Override
	public boolean isAlive() {
		return this.life > 0;
	}
	
	protected void decreaseLife() {
		this.life--;
	}

	protected int getLife() {
		return this.life;
	}

	protected void performMovement(Move move) {
		
		this.pos = this.pos.newPos(move);
	}
	
	public boolean isOut() {
	
		return !this.pos.posValida();
	}

	
	@Override
	public boolean isOnPosition(Position pos) {
		
		return this.pos.equals(pos);
	}
	
	protected abstract String getSymbol();
	protected abstract int getDamage();
	protected abstract int getArmour();
	
	@Override
	public String toString() {
		return getSymbol();
	}
			
	public abstract void onDelete();
	public abstract void automaticMove();
	public void computerAction() {};
	
	@Override
	public boolean performAttack(GameItem other) {return false;}
	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {return false;}

	@Override
	public boolean receiveAttack(UCMWeapon weapon) {return false;} // weapon.isOnPosition(pos);}

}