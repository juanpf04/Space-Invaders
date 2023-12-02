package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.logic.GameWorld;
import tp1.view.Messages;
import tp1.logic.Move;

public class UCMShip extends Ship{
	
	public static final int DAMAGE = 1;
	public static final int LIVES = 3;
	private boolean shockWaveEnabled;
	
	public UCMShip(GameWorld game, Position pos) {
		
		super(game, pos, LIVES, DAMAGE, null);
		this.shockWaveEnabled = false;
	}
	
	@Override
	public String getSymbol() {
		
		if(super.isAlive())
			return Messages.UCMSHIP_SYMBOL;
		
		return Messages.UCMSHIP_DEAD_SYMBOL;
	}
	
	@Override
	public String getDescription() {
		return Messages.UCMSHIP_DESCRIPTION;
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
	public String getInfo() {
		return Messages.ucmShipDescription(getDescription(), getDamage(), getArmour());
	}
	
	@Override
	public int getLife() { 
		return super.getLife();
	}
	
	@Override
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
	public void automaticMove() {}

	
	public void shootShockWave()
	{
		ShockWave shockwave = new ShockWave();
		this.game.addObject(shockwave);
	}

	public String shockWaveState() {
		String state = "OFF";
		
		if(this.shockWaveEnabled)
			state = "ON";
		
		return state;
	}

}
