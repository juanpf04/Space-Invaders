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
	
	private boolean bombEnabled;

	//TODO fill your code
	public DestroyerAlien(Position pos,Game game, AlienManager alienManager) {
		
		super(game, pos, LIVES, DAMAGE, Move.LEFT, alienManager);
		this.bombEnabled = true;
	}
	
	@Override
	public String getSymbol() {
		
		return Messages.status(Messages.DESTROYER_ALIEN_SYMBOL, this.life);
	}
	
	@Override
	public void automaticMove() {
		
		super.automaticMove();
		
		if(this.isAlive())
			this.computerAction();
	}
	
	public boolean receiveAttack(UCMLaser laser) {
		boolean recieveAttack = laser.isOnPosition(this.pos);
		
		if(recieveAttack)
			this.decreaseLive();
		
		return recieveAttack;
	}
	
	public void decreaseLive() {
		
		this.life--;
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
