package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class AlienShip extends EnemyShip{

	protected AlienManager alienManager;
	protected int cyclesToMove;
	protected int speed;
	
	public AlienShip(GameWorld game, Position pos, int lives, int damage, Move dir, AlienManager alienManager) {
		super(game, pos, lives, damage, dir);
		this.alienManager = alienManager;
		this.speed = game.getLevel().getNumCyclesToMoveOneCell();
		this.cyclesToMove = this.speed;
	}

	@Override
	public void automaticMove() {
		
		if(this.cyclesToMove == 0)
		{
			this.performMovement(this.dir);
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
		
		this.performMovement(Move.DOWN);
		this.dir = this.dir.flip();
		this.game.checkLaserAttack(this);
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
}
