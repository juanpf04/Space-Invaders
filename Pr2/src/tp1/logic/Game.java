package tp1.logic;

import java.util.Random;
import tp1.logic.gameobjects.UCMShip;
import tp1.logic.gameobjects.AlienShip;
import tp1.logic.gameobjects.Bomb;
import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.gameobjects.ShockWave;
import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.UCMLaser;
import tp1.logic.gameobjects.Ufo;
import tp1.logic.Level;
import tp1.logic.AlienManager;
import tp1.logic.gameobjects.Ufo; 
import tp1.logic.GameObjectContainer;
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
	private boolean laserEnabled;
	
	public Game(Level level, long seed) {
		
		this.level = level;
		this.seed = seed;
		this.initGame();
	}
	
	
	private final void initGame() {
		
		this.laserEnabled = true;
		this.random = new Random(this.seed);
		this.currentCycle = 0;
		this.points = 0;
		this.exit = false;
		this.alienManager = new AlienManager(this);
		this.player = new UCMShip(this, new Position(DIM_X / 2, DIM_Y - 1));
		this.container = alienManager.initialize();
		this.container.add(player);
	}
	
	@Override
	public String infoToString() {
		StringBuilder list = new StringBuilder();
										// cambiar en un futuro
		list.append(Messages.ucmShipDescription(Messages.UCMSHIP_DESCRIPTION, UCMShip.DAMAGE, UCMShip.LIVES)).append(Messages.LINE_SEPARATOR)
		.append(Messages.alienDescription(Messages.REGULAR_ALIEN_DESCRIPTION, RegularAlien.POINTS, RegularAlien.DAMAGE, RegularAlien.LIVES)).append(Messages.LINE_SEPARATOR)
		.append(Messages.alienDescription(Messages.DESTROYER_ALIEN_DESCRIPTION, DestroyerAlien.POINTS, DestroyerAlien.DAMAGE, DestroyerAlien.LIVES)).append(Messages.LINE_SEPARATOR)	
		.append(Messages.alienDescription(Messages.UFO_DESCRIPTION, Ufo.POINTS, Ufo.DAMAGE, Ufo.LIVES)).append(Messages.LINE_SEPARATOR); 
		
		return list.toString();
	} 
	
	@Override
	public String stateToString() {
		StringBuilder state = new StringBuilder();
		
		state.append(Messages.life(this.player.getLife())).append(Messages.LINE_SEPARATOR)
		.append(Messages.points(this.points)).append(Messages.LINE_SEPARATOR)
		.append(Messages.shockWaveStatus(this.player.shockWaveState())).append(Messages.LINE_SEPARATOR);	
		
		return state.toString();
	} 

	@Override
	public int getCycle() {
		
		return this.currentCycle;
	}
	
	@Override
	public int getRemainingAliens() {
		
		return this.alienManager.getRemainingAliens();
	}

	@Override
	public String positionToString(int col, int row) {
		return container.toString(new Position(col, row));
	}

	@Override
	public boolean isFinished() {
		
		return this.exit || this.playerWin() || this.aliensWin();
	}
	
	@Override
	public void exit() {
		
		this.exit = true;
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


	public Random getRandom() {
		
		return this.random;
	}

	
	public Level getLevel() {
		
		return this.level;
	}
	
	@Override
	public boolean move(Move move) {
		boolean canMove = this.player.validPos(move) && this.player.canMove(move);
		
		if(canMove) 
			this.player.performMovement(move);
		
		return canMove;
	}
	
	@Override
	public boolean shootLaser() {
		boolean canShoot = this.laserIsEnable();
		
		if(canShoot)
			this.player.shootLaser();
		
		return canShoot;
	}
	
	
	public boolean laserIsEnable() {
		
		return laserEnabled;
	}
	
	
	public void enableLaser() {
		
		this.laserEnabled = true;
	}
	
	public void addObject(GameObject object) {
	    this.container.add(object);
	}

	@Override
	public void reset() {
		
		this.initGame();
	} 

	
	public void receivePoints(int points) {
		
		this.points += points;
	}


	public void enableShockWave() {
		
		this.shockWave.enable();
	}

	@Override
	public boolean shootShockWave() {
		return this.container.shootShockWave();
	}

//	@Override
//	public void update() {
//		
//		this.regularAlienList.automaticMove();	
//		this.destroyerAlienList.automaticMove();
//		
//		this.ufo.computerAction();
//		this.ufo.automaticMove();
//		
//		this.bombList.automaticMove();
//		
//		if(!this.laserIsEnable())
//			this.bombList.performAttack(this.ucmLaser);
//		
//		this.moveLaser();
//		
//		if(!this.laserIsEnable())  {
//			this.regularAlienList.checkLaserAttack(this.ucmLaser);
//			if(!this.laserIsEnable()) {
//				this.destroyerAlienList.checkLaserAttack(this.ucmLaser);
//				if(!this.laserIsEnable()) {
//					this.ucmLaser.performAttack(ufo);
//					if(!this.laserIsEnable())
//						this.bombList.performAttack(this.ucmLaser);		
//				}
//			}
//		}
//		
//		this.bombList.performAttack(this.player);
//		
//		this.currentCycle++;
//	}
	
	@Override
	public void update() {
	    this.currentCycle++;
	    this.container.computerActions();
	    this.container.automaticMoves();
	}
}
