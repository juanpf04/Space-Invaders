package tp1.logic;

import tp1.control.InitialConfiguration;
import tp1.exception.InitializationException;
import tp1.exception.LaserInFlightException;
import tp1.exception.NoShockWaveException;
import tp1.exception.NotAllowedMoveException;
import tp1.exception.NotEnoughtPointsException;
import tp1.exception.OffWorldException;

public interface GameModel {

	public boolean isFinished();
	public void update();
	// PLAYER ACTIONS
	public void move(Move move) throws OffWorldException, NotAllowedMoveException;
	
	public void shootLaser() throws LaserInFlightException;
	public void shockWave() throws NoShockWaveException;
	public void shootSuperLaser() throws LaserInFlightException, NotEnoughtPointsException;
	
	public void reset(InitialConfiguration conf) throws InitializationException;
	public void exit();
	
	public String list();
}
