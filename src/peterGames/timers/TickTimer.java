package peterGames.timers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import errorHandler.ErrorLogger;

public class TickTimer extends Thread {
	
	long del;
	long next;
	boolean running;
	long frame;
	int fps;
	@SuppressWarnings("unused")
	private List<Long> vals;
	private Map<UUID, TickEvent> todo;
	private ErrorLogger Eloger;
	private String type;
	
	public TickTimer(int tps, ErrorLogger logger, String Type) {
		super("TickTimer");
		type = Type;
		Eloger = logger;
		//System.out.println("New Tick Timer");
		this.fps = tps;
		del = 1000000000 / this.fps;
		frame = -1;
		next = System.nanoTime() + del;
		running = true;
		//System.out.println("Asked to start ticking tps=" + tps + ". Ticking will begin in a moment");
		vals = new ArrayList<Long>();
		todo = new HashMap<UUID, TickEvent>();
		//super.start();
		addToDo(new TickEvent() { public void tick(long frame) { } }, UUID.randomUUID());
	}
	
	public void addToDo(TickEvent task, UUID taskUUID) {
		//System.out.println("Adding Tick ToDo");
		todo.put(taskUUID, task);
	}
	public void removeToDo(UUID taskUUID) {
		todo.remove(taskUUID);
	}
	
	public void end() {
		running = false;
		//System.out.println("Stopping ticker");
	}
	
	public void settps(int tps) {
		fps = tps;
		del = 1000000000 / this.fps;
	}
	
	private long startTick = 0;
	
	@Override
	public void run() {
		//System.out.println("Starting ticker with period " + del + "ns");
		next = System.nanoTime();
		startTick = System.nanoTime();
		int ticks = 0;
		while(running) {
			if(System.nanoTime() - next >= 0) {
				
				long laststart = startTick;
				startTick = System.nanoTime();
				@SuppressWarnings("unused")
				long ttime = startTick - laststart;
				
				long startticks = System.nanoTime();
				
				for(TickEvent t : todo.values()) {
					t.tick(frame);
				}
				ticks++;
				
				long tickstime = System.nanoTime() - startticks;
				
				// code
				
				next = next + del;
				frame++;
				
				if(tickstime > del) {
					long behind = next;
					int dropCount = 0;
					while(next < System.nanoTime()) {
						next = next + del;
						frame++;
						dropCount++;
					}
					
					if(dropCount > 5) {
						Eloger.logError("Dropping " + type + " Running " + (double)((long)(System.nanoTime()-behind)/1000)/1000 + "ms behind.","TickTimer.java",90, "Skipping " + dropCount + " " + type + "s ; " + ticks);
					}
				}
				
			} else {
				long del = next - System.nanoTime();
				//del = Long.MAX_VALUE;
				if(del > 1) {
					try {
						long ms = del / 1000000;
						int ns = (int) (del % 1000000);
						Thread.sleep(ms, ns);
//						Thread.sleep(del);
					} catch (InterruptedException e) { System.out.println("Tick sleep interrupted"); }
				}
			}
		} // End loop
		System.out.println("Timer has stopped");
	}
}
