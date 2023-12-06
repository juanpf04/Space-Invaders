package tp1.logic;

public interface GameModel {

	public boolean isFinished();
	public void update();
	// PLAYER ACTIONS
	public boolean move(Move move);
	public boolean shootLaser();
	public boolean shockWave();
	public boolean shootSuperLaser();
	public void reset();
	public void exit();
	public String infoToString(); // tb esta en status
}
