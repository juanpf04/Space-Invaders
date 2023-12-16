package tp1.logic;

import java.util.Random;
import tp1.logic.gameobjects.UCMShip;
import tp1.logic.gameobjects.AlienShip;
import tp1.logic.gameobjects.ShipFactory;
import tp1.logic.gameobjects.GameObject;
import tp1.control.InitialConfiguration;
import tp1.exception.InitializationException;
import tp1.exception.LaserInFlightException;
import tp1.exception.NoShockWaveException;
import tp1.exception.NotAllowedMoveException;
import tp1.exception.NotEnoughtPointsException;
import tp1.exception.OffWorldException;
import tp1.view.Messages;

public class Game implements GameModel, GameStatus, GameWorld {

	public static final int DIM_X = 9;
	public static final int DIM_Y = 8;

	private Random random;
	private Level level;
	private long seed;
	private int currentCycle;
	private GameObjectContainer container;
	private UCMShip player;
	private AlienManager alienManager;
	private int points;
	private boolean exit;
	
	public Game(Level level, long seed) {
		
		this.level = level;
		this.seed = seed;
		try {
			this.initGame(InitialConfiguration.NONE); // revisar
		} catch (InitializationException e) {}
	}
	
	private final void initGame(InitialConfiguration conf) throws InitializationException {
		
		this.random = new Random(this.seed);
		this.currentCycle = 0;
		this.points = 0;
		this.exit = false;
		this.alienManager = new AlienManager(this);
		this.player = new UCMShip(this, new Position(DIM_X / 2, DIM_Y - 1));
		this.container = alienManager.initialize(conf);
		this.container.add(player);
	}
	
	
	// GAME MODEL
	
	@Override
	public boolean isFinished() {
		return this.exit || this.playerWin() || this.aliensWin();
	}
	
	@Override
	public void update() {
	    this.currentCycle++;
	    this.container.computerActions();
	    this.container.automaticMoves();
	}
	
	@Override
	public void move(Move move) throws OffWorldException, NotAllowedMoveException {
		this.player.move(move);
	}
	
	// SHOCKWAVE
	
	@Override
	public void enableShockWave() {
		this.player.enableShockWave();
	}
	
	@Override
	public void shockWave() throws NoShockWaveException {
		this.player.shockWave();
	}
	
	
	// SUPERLASER
	
	@Override
	public void shootSuperLaser() throws LaserInFlightException, NotEnoughtPointsException {
		this.player.shootSuperLaser();
	}
	
	
	// LASER
	
	@Override
	public void enableLaser() {
		this.player.enableLaser();
	}
	
	@Override
	public void shootLaser() throws LaserInFlightException {
		this.player.shootLaser();
	}
	
	
	
	@Override
	public void reset(InitialConfiguration conf) throws InitializationException {
		this.initGame(conf);
	} 
	
	@Override
	public void exit() {
		this.exit = true;
	}
	
	@Override
	public String list() {
		return this.infoToString();
	}
	
	
	// GAME STATUS
	
	@Override
	public String positionToString(int col, int row) {
		Position pos = new Position(col, row);
		String symbol = "";
		
		if(this.player.isOnPosition(pos))
			symbol = this.player.getSymbol();
		else
			symbol = container.toString(pos);
		
		return symbol;
	}
	
	@Override
	public String infoToString() {
		return ShipFactory.getInfo();
	} 
	
	@Override
	public String stateToString() {
		StringBuilder state = new StringBuilder();
		
		state
		.append(Messages.numberOfCycles(this.getCycle())).append(Messages.LINE_SEPARATOR)
		.append(Messages.life(this.player.getLife())).append(Messages.LINE_SEPARATOR)
		.append(Messages.points(this.points)).append(Messages.LINE_SEPARATOR)
		.append(Messages.shockWaveStatus(this.player.shockWaveState())).append(Messages.LINE_SEPARATOR)	
		.append(Messages.remainingAliens(this.getRemainingAliens())).append(Messages.LINE_SEPARATOR);
		
		return state.toString();
	} 

	@Override
	public boolean playerWin() {
		return this.player.isAlive() 
				&& this.getRemainingAliens() == 0;
	}

	@Override
	public boolean aliensWin() {
		return this.getRemainingAliens() > 0 
				&& !this.player.isAlive() || this.alienManager.squadInFinalRow();
	}
	
	
	// GAME WORLD
	
	public int getCycle() {
		return this.currentCycle;
	}
	
	@Override
	public int getRemainingAliens() {
		return this.alienManager.getRemainingAliens();
	}

	@Override
	public Random getRandom() {
		return this.random;
	}

	@Override
	public Level getLevel() {
		return this.level;
	}
	
	@Override
	public void addObject(GameObject object) {
	    this.container.add(object);
	}
	
	@Override
	public void receivePoints(int points) {
		this.points += points;
	}

	@Override
	public int getPoints() {
		return this.points;
	}
	
	@Override
	public void checkAttack(AlienShip alienShip) {
		this.container.performAttack(alienShip);
	}

	@Override
	public int containerSize() {
		return this.container.size();
	}
	
}
