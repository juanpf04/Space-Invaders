package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;


public class Ufo extends EnemyShip{

	public static final int DAMAGE = 0;
	public static final int LIVES = 1;
	public static final int POINTS = 25;
	
	private boolean enabled;
	
	public Ufo(Game game) {
		super(game, new Position(Game.DIM_X, 0), LIVES, DAMAGE, Move.LEFT);
		this.enabled = false;
	}
	

	@Override
	public String getSymbol() {
		
		return Messages.status(Messages.UFO_SYMBOL, super.life);
	}
	
	@Override
	public void computerAction() {
		if(!enabled && canGenerateRandomUfo()) {
			enable();	
		}
	}
	
	@Override
	public void automaticMove () {
		if(this.isEnable()) {
			performMovement(dir);
			if(isOut())
				onDelete();
		}
	}
	
	
	private void enable() {
		
		this.enabled = true;
	}

	
	
	public void onDelete() {
		
		this.enabled = false;
		this.pos = new Position(Game.DIM_X, 0);
		super.life = LIVES;
	}

	
	private boolean canGenerateRandomUfo(){
		
		return game.getRandom().nextDouble() < game.getLevel().getUfoFrequency();
	}

	
	public boolean isEnable() {
	
		return this.enabled;
	}

	
	public void checkAttack(UCMLaser ucmLaser) {
		
		if(ucmLaser.performAttack(this)) {
			this.decreaseLife();
		}
	}
	
	
	public boolean receiveAttack(UCMLaser ucmLaser) {
		
		return ucmLaser.isOnPosition(this.pos);
	}
	
	@Override
	public void decreaseLife() {
		
		super.decreaseLife();
		if(!this.isAlive()) {
			this.onDelete();
			this.game.enableShockWave();
			this.game.addPoints(POINTS);
		}
	}


	@Override
	protected int getDamage() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	protected int getArmour() {
		// TODO Auto-generated method stub
		return 0;
	}

}
