package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.logic.GameWorld;
import tp1.view.Messages;
import tp1.logic.Move;

public class UCMShip extends Ship{
	
	public static final int DAMAGE = 1;
	public static final int LIVES = 3;
	private boolean shockWaveEnabled;
	private boolean laserEnabled;
	
	public UCMShip(GameWorld game, Position pos) {
		
		super(game, pos, LIVES, DAMAGE, null);
		this.shockWaveEnabled = false;
		this.laserEnabled = true;
	}
	
	@Override
	public String getSymbol() {
		
		if(this.isAlive())
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
	
	private boolean shockWaveIsEnable() {
		return this.shockWaveEnabled;
	}
	
	public void enableShockWave() {
		this.shockWaveEnabled = true;
	}

	private boolean laserIsEnable() {
		return this.laserEnabled;
	}
	
	public void enableLaser() {
		this.laserEnabled = true;
	}
	
	public boolean shootLaser() {
		boolean shoot = this.laserIsEnable();
		
		if(shoot) {
			UCMLaser ucmLaser = new UCMLaser(this.game, this.pos);
			this.game.addObject(ucmLaser);
			this.laserEnabled = false;			
		}
		
		return shoot;
	}

	public boolean shootShockWave() {
		boolean shoot = this.shockWaveIsEnable();
		
		if(shoot) {
			ShockWave shockWave = new ShockWave(this.game);
			this.game.addObject(shockWave);
			this.shockWaveEnabled = false;			
		}
		
		return shoot;
	}

	public String shockWaveState() {
		String state = "OFF";
		
		if(this.shockWaveIsEnable())
			state = "ON";
		
		return state;
	}
	
	public boolean receiveAttack(EnemyWeapon other) {
		boolean recieveAttack = other.isAlive() && other.isOnPosition(this.pos);
		
		if(recieveAttack)
			this.decreaseLife();
		
		return recieveAttack;
	}

	@Override
	public void automaticMove() {}


}
