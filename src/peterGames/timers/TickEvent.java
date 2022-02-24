package peterGames.timers;

public interface TickEvent {
	
	/**
	 * Called on tick
	 * @param frame
	 */
	public abstract void tick(long frame);
}
