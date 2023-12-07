package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public abstract class AlienShip extends EnemyShip {

	protected AlienManager alienManager;
	protected int cyclesToMove;
	protected int speed;
	
	public AlienShip(GameWorld game, Position pos, int lives, Move dir, AlienManager alienManager) {
		super(game, pos, lives, dir);
		this.alienManager = alienManager;
		this.speed = game.getLevel().getNumCyclesToMoveOneCell();
		this.cyclesToMove = this.speed;
	}
	
	public AlienShip() {}

	protected abstract AlienShip copy(GameWorld game, Position pos, AlienManager am);
	
	@Override
	public void automaticMove() {
		
		if(this.cyclesToMove == 0)
		{
			this.performMovement();
			this.cyclesToMove = this.speed; 
			if(this.isInBorder())
				this.alienManager.shipOnBorder();	
		}
		else if(this.alienManager.onBorder())
			this.descent();
		
		else
			this.cyclesToMove--;
	}
	
	protected void descent() {
		Move move = this.dir;
		this.dir = Move.DOWN;
		this.performMovement();
		this.dir = move.flip();
		this.game.checkAttack(this);
		if(this.isAlive())
			this.alienManager.decreaseOnBorder();
		if(this.isInFinalRow())
			this.alienManager.isInFinalRow();
	}
	
	protected boolean isInFinalRow() {
		return this.pos.inFinalRow();
	}
	
	protected boolean isInBorder() {
		return this.pos.isInBorder();
	}
	
	@Override
	public String toString() {
		return Messages.status(getSymbol(), getLife());
	}
	
}
