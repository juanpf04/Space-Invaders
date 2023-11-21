package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class GameObject implements GameItem {

	protected Position pos;
	protected int life;
	protected Game game;
	
	public GameObject(Game game, Position pos, int life) {	
		this.pos = new Position(pos);
		this.game = game;
		this.life = life;
	}
	
	@Override
	public boolean isAlive() {
		return this.life > 0;
	}

	protected int getLife() {
		return this.life;
	}

	protected void performMovement(Move move) {
		
		this.pos = this.pos.newPos(move);
	}
	
	protected void die() {
		
		this.onDelete();
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
	
			
	public abstract void onDelete();
	public abstract void automaticMove();
	public void computerAction() {};
	
	//TODO fill with your code
	
	@Override
	public boolean performAttack(GameItem other) {return false;}
	
	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {return false;}

	@Override
	public boolean receiveAttack(UCMWeapon weapon) {return false;}

}