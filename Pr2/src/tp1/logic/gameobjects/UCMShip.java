package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.logic.Game;
import tp1.view.Messages;
import tp1.logic.Move;

public class UCMShip extends Ship{
	
	public static final int DAMAGE = 1;
	public static final int LIVES = 3;
	
	public UCMShip(Game game, Position pos) {
		
		super(game, pos, LIVES, DAMAGE, null);
	}
	
	@Override
	public String getSymbol() {
		
		if(super.isAlive())
			return Messages.UCMSHIP_SYMBOL;
		
		return Messages.UCMSHIP_DEAD_SYMBOL;
	}
	
	
	public int getLife() { 
		return super.getLife();
	}
	
	public void performMovement(Move move) {
		
		super.performMovement(move);
	}
	
	public boolean canMove(Move move) {
		
		return	!move.equals(Move.UP) 
				&& !move.equals(Move.DOWN);
	}
	
	public boolean validPos(Move move) {
		
		return pos.validPos(move);
	}
	
	public void shootLaser() {
		
		UCMLaser ucmlaser = new UCMLaser(this.game, this.pos);
		this.game.addObject(ucmlaser);
	}

	
	public boolean receiveAttack(EnemyWeapon other) {
		boolean recieveAttack = other.isAlive() && other.isOnPosition(this.pos);
		
		if(recieveAttack)
			super.decreaseLife();
		
		return recieveAttack;
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
	public void automaticMove() {}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
