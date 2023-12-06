package tp1.logic;

import tp1.control.InitialConfiguration;

public interface GameModel {

	public boolean isFinished();
	public void update();
	// PLAYER ACTIONS
	public boolean move(Move move);
	
	public boolean shootLaser();
	public boolean shockWave();
	public boolean shootSuperLaser();
	
	public void reset(InitialConfiguration conf);
	public void exit();
	
	public String list();
}
