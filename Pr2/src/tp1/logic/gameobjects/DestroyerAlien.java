package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class DestroyerAlien extends AlienShip{
	
	public static final int DAMAGE = 1;
	public static final int LIVES = 1;
	public static final int POINTS = 10;
	
	private int cyclesToMove;
	private int speed;
	private Move dir;
	private Position pos;
	private int lives;
	private Game game;
	private boolean bombEnabled;
	private AlienManager alienManager;

	//TODO fill your code
	public DestroyerAlien(Position pos,Game game, AlienManager alienManager) {
		
		this.pos = pos;
		this.game = game;
		this.speed = game.getLevel().getNumCyclesToMoveOneCell();
		this.cyclesToMove = this.speed;
		this.dir = Move.LEFT;
		this.lives = LIVES;
		this.alienManager = alienManager;
		this.bombEnabled = true;
	}
	
	@Override
	public String getSymbol() {
		
		return Messages.status(Messages.DESTROYER_ALIEN_SYMBOL, this.lives);
	}
	
	/**
	 *  Implements the automatic movement of the destroyer alien	
	 */
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
		
		if(this.isAlive())
			this.computerAction();
	}

	
	private void descent() {
		
		this.performMovement(Move.DOWN);
		this.dir = this.dir.flip();
		this.game.checkLaserAttack(this);
		if(this.isAlive())
			this.alienManager.decreaseOnBorder();
		if(this.isInFinalRow())
			this.alienManager.isInFinalRow();
	}

	
	private boolean isInFinalRow() {

		return this.pos.inFinalRow();
	}
	
	private boolean isInBorder() {
		
		return this.pos.isInBorder();
	}

	
	public boolean receiveAttack(UCMLaser laser) {
		boolean recieveAttack = laser.isOnPosition(this.pos);
		
		if(recieveAttack)
			this.decreaseLive();
		
		return recieveAttack;
	}
	
	public void decreaseLive() {
		
		this.lives--;
		if(!this.isAlive()) {
			this.alienManager.decreaseRemainingAliens();
			this.game.addPoints(POINTS);
		}
	}

	
	public boolean bombEnabled() {
		
		return this.bombEnabled;
	}

	
	public void deleteBomb() {
		
		this.bombEnabled = true;
	}

	@Override
	public void computerAction() {
		
		if(this.bombEnabled && this.canGenerateRandomShoot()) {
			
			this.game.addObject(new Bomb(this.pos, this.game, this));
			this.bombEnabled = false; 
		}
			
	}
	
	
	private boolean canGenerateRandomShoot(){
		
		return game.getRandom().nextDouble() < game.getLevel().getShootFrequency();
	}
	
}
