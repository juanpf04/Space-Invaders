package tp1.logic;

public interface GameModel {

	public boolean isFinished();
	public void update();
	// PLAYER ACTIONS
	public boolean move(Move move);
	public boolean shootLaser();
	public boolean shootShockWave();
	public void reset();
	public void exit();
}
