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
	
	
	public void performMovement(Move move) {
		
		this.pos = this.pos.newPos(move);
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

	
	public boolean receiveAttack(Bomb bomb) {
		boolean recieveAttack = bomb.inPos(this.pos);
		
		if(recieveAttack)
			super.life--;
		
		return recieveAttack;
	}

}
