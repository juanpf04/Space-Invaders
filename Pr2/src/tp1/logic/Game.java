package tp1.logic;

import java.util.Random;
import tp1.logic.gameobjects.UCMShip;
import tp1.logic.gameobjects.AlienShip;
import tp1.logic.gameobjects.Bomb;
import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.gameobjects.ShockWave;
import tp1.logic.gameobjects.DestroyerAlien; 
import tp1.logic.gameobjects.UCMLaser;
import tp1.logic.gameobjects.Ufo;
import tp1.logic.Level;
import tp1.logic.AlienManager;
import tp1.logic.gameobjects.Ufo; 
import tp1.logic.lists.RegularAlienList;
import tp1.logic.lists.BombList;
import tp1.logic.lists.DestroyerAlienList;
import tp1.view.Messages;

public class Game implements GameModel, GameStatus, GameWorld {

	public static final int DIM_X = 9;
	public static final int DIM_Y = 8;
	
	

	private Random random;
	private Level level;
	private long seed;
	private int cycle;
	private UCMShip ucmShip;
	private UCMLaser ucmLaser;
	private RegularAlienList regularAlienList;
	private DestroyerAlienList destroyerAlienList;
	private BombList bombList;
	private AlienManager alienManager;
	private int points;
	private Ufo ufo;
	private ShockWave shockWave;
	private boolean exit;
	
	
	public Game(Level level, long seed) {
		
		this.level = level;
		this.seed = seed;
		this.initialize();
	}
	
	
	private final void initialize() {
		
		this.random = new Random(this.seed);
		this.cycle = 0;
		this.points = 0;
		this.ucmShip = new UCMShip(this, new Position(DIM_X / 2, DIM_Y - 1));
		this.shockWave = new ShockWave();
		this.alienManager = new AlienManager(this);
		//
		this.ucmLaser = null;
		this.ufo = new Ufo(this);
		this.regularAlienList = this.alienManager.initializeRegularAliens();
		this.destroyerAlienList = this.alienManager.initializeDestroyerAliens();
		this.bombList = new BombList(this.level);
		// cambiar por el container
		this.exit = false;
	}
	
	@Override
	public String infoToString() {
		StringBuilder list = new StringBuilder();
		
		list.append(Messages.ucmShipDescription(Messages.UCMSHIP_DESCRIPTION, UCMShip.DAMAGE, UCMShip.LIVES)).append(Messages.LINE_SEPARATOR)
		.append(Messages.alienDescription(Messages.REGULAR_ALIEN_DESCRIPTION, RegularAlien.POINTS, RegularAlien.DAMAGE, RegularAlien.LIVES)).append(Messages.LINE_SEPARATOR)
		.append(Messages.alienDescription(Messages.DESTROYER_ALIEN_DESCRIPTION, DestroyerAlien.POINTS, DestroyerAlien.DAMAGE, DestroyerAlien.LIVES)).append(Messages.LINE_SEPARATOR)	
		.append(Messages.alienDescription(Messages.UFO_DESCRIPTION, Ufo.POINTS, Ufo.DAMAGE, Ufo.LIVES)).append(Messages.LINE_SEPARATOR); 
		
		return list.toString();
	} 
	
	@Override
	public String stateToString() {
		StringBuilder state = new StringBuilder();
		
		state.append(Messages.life(this.ucmShip.getLife())).append(Messages.LINE_SEPARATOR)
		.append(Messages.points(this.points)).append(Messages.LINE_SEPARATOR)
		.append(Messages.shockWaveStatus(this.shockWave.stateToString())).append(Messages.LINE_SEPARATOR);	
		
		return state.toString();
	} 

	@Override
	public int getCycle() {
		
		return this.cycle;
	}
	
	@Override
	public int getRemainingAliens() {
		
		return this.alienManager.getRemainingAliens();
	}

	@Override
	public String positionToString(int col, int row) {
		Position pos = new Position(col,row);
		String symbol = "";
		
		if(!this.laserIsEnable() && this.ucmLaser.isOnPosition(pos)) 
			symbol = this.ucmLaser.getSymbol();
		
		else if(this.regularAlienList.alienInPos(pos) != -1)
			symbol = this.regularAlienList.showAlienInPos(pos);
		
		else if(this.destroyerAlienList.alienInPos(pos) != -1)
			symbol = this.destroyerAlienList.showAlienInPos(pos);
		
		else if(this.ucmShip.isOnPosition(pos))
			symbol = this.ucmShip.getSymbol();
		
		else if(this.ufo.isOnPosition(pos))
			symbol = this.ufo.getSymbol();
		
		else if(this.bombList.bombInPos(pos) != -1)
			symbol = this.bombList.showbombInPos(pos);
		
		return symbol;
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
		
		return this.ucmShip.isAlive() 
				&& this.getRemainingAliens() == 0;
	}

	@Override
	public boolean aliensWin() {
		
		return this.getRemainingAliens() > 0 
				&& !this.ucmShip.isAlive() || this.alienManager.squadInFinalRow();
	}


	public Random getRandom() {
		
		return this.random;
	}

	
	public Level getLevel() {
		
		return this.level;
	}
	
	@Override
	public boolean move(Move move) {
		boolean canMove = this.ucmShip.validPos(move) && this.ucmShip.canMove(move);
		
		if(canMove) 
			this.ucmShip.performMovement(move);
		
		return canMove;
	}
	
	@Override
	public boolean shootLaser() {
		boolean canShoot = this.laserIsEnable();
		
		if(canShoot)
			this.ucmShip.shootLaser();
		
		return canShoot;
	}
	
	
	public boolean laserIsEnable() {
		
		return this.ucmLaser == null;
	}
	
	
	public void enableLaser() {
		
		this.ucmLaser = null;
	}
	
	
	public void moveLaser() {
		if(!this.laserIsEnable())
			this.ucmLaser.automaticMove();
	}
	
	
	public void addObject(Bomb bomb) {
		
		this.bombList.addObject(bomb);
	}
	
	
	public void addObject(UCMLaser ucmlaser) {

		this.ucmLaser = ucmlaser;
	}

	@Override
	public void reset() {
		
		this.initialize();
	}

	
	public void addPoints(int points) {
		
		this.points += points;
	}


	public void enableShockWave() {
		
		this.shockWave.enable();
	}

	@Override
	public boolean shootShockWave() {
		boolean canShoot = this.shockWave.isEnabled();
		
		if(canShoot)
			this.shockWave.performAttack(this.regularAlienList, this.destroyerAlienList);
		
		return canShoot;
	}

	@Override
	public void update() {
		
		this.regularAlienList.automaticMove();	
		this.destroyerAlienList.automaticMove();
		
		this.ufo.computerAction();
		this.ufo.automaticMove();
		
		this.bombList.automaticMove();
		
		if(!this.laserIsEnable())
			this.bombList.performAttack(this.ucmLaser);
		
		this.moveLaser();
		
		if(!this.laserIsEnable())  {
			this.regularAlienList.checkLaserAttack(this.ucmLaser);
			if(!this.laserIsEnable()) {
				this.destroyerAlienList.checkLaserAttack(this.ucmLaser);
				if(!this.laserIsEnable()) {
					this.ufo.checkAttack(this.ucmLaser);
					if(!this.laserIsEnable())
						this.bombList.performAttack(this.ucmLaser);		
				}
			}
		}
		
		this.bombList.performAttack(this.ucmShip);
		
		this.cycle++;
	}


	public void checkLaserAttack(AlienShip alienShip) {
		
		if(!this.laserIsEnable())
			this.ucmLaser.performAttack(alienShip);
	}
}
