package tp1.logic.gameobjects;

import tp1.logic.Position;

import java.util.Arrays;
import java.util.List;

import tp1.exception.LaserInFlightException;
import tp1.exception.NoShockWaveException;
import tp1.exception.NotAllowedMoveException;
import tp1.exception.NotEnoughtPointsException;
import tp1.exception.OffWorldException;
import tp1.logic.GameWorld;
import tp1.view.Messages;
import tp1.logic.Move;

public class UCMShip extends Ship {
	
	public static final int DAMAGE = 1;
	public static final int LIVES = 3;
	public static final int SUPERLASER_COST = 5;
	private static final List<Move> ALLOWED_MOVES = Arrays.asList(
		Move.LLEFT,
		Move.LEFT,
		Move.NONE,
		Move.RIGHT,
		Move.RRIGHT
	);
	private boolean shockWaveEnabled;
	private boolean laserEnabled;
	private boolean superLaserEnabled;
	
	public UCMShip(GameWorld game, Position pos) {
		super(game, pos, LIVES, Move.NONE);
		this.shockWaveEnabled = false;
		this.laserEnabled = true;
		this.superLaserEnabled = true;
	}
	
	protected UCMShip() {}

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
	
	public void move(Move move) throws OffWorldException, NotAllowedMoveException {
		if(!this.allowedMove(move))
			throw new NotAllowedMoveException(Messages.DIRECTION_ERROR + move.name()
					+ Messages.LINE_SEPARATOR + Messages.ALLOWED_MOVES_MESSAGE); 
		if(!this.validPos(move))
				throw new OffWorldException(Messages.OFF_WORLD_MESSAGE.formatted(move.name(), this.pos.toString()));
		
		dir = move;
		this.performMovement();
		dir = Move.NONE;

	}
	
	private boolean allowedMove(Move move) {
		for(Move m: ALLOWED_MOVES)
			if(move.equals(m)) return true;
		return false;
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
	
	public void shootLaser() throws LaserInFlightException {
		if(!this.laserIsEnable())
			throw new LaserInFlightException(Messages.LASER_ERROR + Messages.LINE_SEPARATOR
					+ Messages.LASER_ALREADY_SHOT);
		
		this.game.addObject(new UCMLaser(this.game, this.pos));
		this.laserEnabled = false;			
	}
	
	
	// SUPERLASER
	
	private boolean superLaserIsEnable() {
		return this.superLaserEnabled;
	}
	
	public void enableSuperLaser() {
		this.superLaserEnabled = true;
	}
	
	public void shootSuperLaser() throws LaserInFlightException, NotEnoughtPointsException {
		if(!this.superLaserIsEnable()) 
			throw new LaserInFlightException(Messages.SUPERLASER_ERROR + Messages.LINE_SEPARATOR
					+ Messages.LASER_ALREADY_SHOT);
		if(this.game.getPoints() < SUPERLASER_COST)
			throw new NotEnoughtPointsException(Messages.SUPERLASER_ERROR + Messages.LINE_SEPARATOR
					+ Messages.NOT_ENOUGH_POINTS_ERROR.formatted(this.game.getPoints(),SUPERLASER_COST));
		
		this.game.addObject(new SuperLaser(this.game, this.pos));
		this.superLaserEnabled = false;		
		this.game.receivePoints(-SUPERLASER_COST);
	}
	
	
	// SHOCKWAVE

	private boolean shockWaveIsEnable() {
		return this.shockWaveEnabled;
	}
	
	public void enableShockWave() {
		this.shockWaveEnabled = true;
	}
	
	public void shockWave() throws NoShockWaveException {
		if(!this.shockWaveIsEnable())
			throw new NoShockWaveException(Messages.SHOCKWAVE_ERROR);
	
		this.game.addObject(new ShockWave(this.game));
		this.shockWaveEnabled = false;			
	}

	public String shockWaveState() {
		String state = "OFF";
		
		if(this.shockWaveIsEnable())
			state = "ON";
		
		return state;
	}

	public static String allowedMoves(String separator) { 
		StringBuilder allowedMoves = new StringBuilder();
		for(Move m: ALLOWED_MOVES)
			allowedMoves.append(m.name().toLowerCase() + separator);
		
		return allowedMoves.substring(0, allowedMoves.length() - separator.length());
	}

}
