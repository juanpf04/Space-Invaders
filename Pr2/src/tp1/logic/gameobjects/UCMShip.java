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
	private boolean superLaserEnabled;
	
	public UCMShip(GameWorld game, Position pos) {
		
		super(game, pos, LIVES, null);
		this.shockWaveEnabled = false;
		this.laserEnabled = true;
		this.superLaserEnabled = true;
	}
	
	@Override
	public String getSymbol() {
		String symbol = Messages.UCMSHIP_SYMBOL;

		if(!this.isAlive())
			symbol = Messages.UCMSHIP_DEAD_SYMBOL;
		
		return symbol;
	}
	
	@Override
	protected String getDescription() {
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
	
	public boolean move(Move move) {
		boolean canMove = this.validPos(move) && this.canMove(move);
		
		if(canMove) 
			this.performMovement(move);
		
		return canMove;
	}
	
	private boolean canMove(Move move) {
		
		return	!move.equals(Move.UP) 
				&& !move.equals(Move.DOWN);
	}
	
	private boolean validPos(Move move) {
		
		return pos.validPos(move);
	}
	
	public boolean receiveAttack(EnemyWeapon other) {
		this.loseLife(other.getDamage());
		return true;
	}

	@Override
	public void automaticMove() {}

	@Override
	public void onDelete() {}
	
	
	// LASER

	private boolean laserIsEnable() {
		return this.laserEnabled;
	}
	
	public void enableLaser() {
		this.laserEnabled = true;
	}
	
	public boolean shootLaser() {
		boolean shoot = this.laserIsEnable();
		
		if(shoot) {
			this.game.addObject(new UCMLaser(this.game, this.pos));
			this.laserEnabled = false;			
		}
		
		return shoot;
	}
	
	
	// SUPERLASER
	
	private boolean superLaserIsEnable() {
		return this.superLaserEnabled;
	}
	
	public void enableSuperLaser() {
		this.superLaserEnabled = true;
	}
	
	public boolean shootSuperLaser() {
		boolean shoot = this.superLaserIsEnable();
		
		if(shoot) {
			this.game.addObject(new SuperLaser(this.game, this.pos));
			this.superLaserEnabled = false;			
		}
		
		return shoot;
	}
	
	
	// SHOCKWAVE

	private boolean shockWaveIsEnable() {
		return this.shockWaveEnabled;
	}
	
	public void enableShockWave() {
		this.shockWaveEnabled = true;
	}
	
	public boolean shockWave() {
		boolean shoot = this.shockWaveIsEnable();
		
		if(shoot) {
			this.game.addObject(new ShockWave(this.game));
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

}
