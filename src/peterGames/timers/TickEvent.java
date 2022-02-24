package peterGames.timers;

/**
 * Event for TickTimer
 * @author Peter Crall
 *
 */
public interface TickEvent {
	
	/**
	 * Called on tick
	 * @param frame
	 */
	public abstract void tick(long frame);
}
