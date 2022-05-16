package threading;

import java.util.concurrent.TimeUnit;

public class PoolThread<I, O> {
	
	private Thread thread;
	private volatile boolean running = false;
	
	public PoolThread(ThreadPool<I, O> pool, PoolRunnable<I, O> runable) {
		thread = new Thread(() -> {
			while(running) {
				try {
//					System.out.println("waiting");
					I in = pool.pollIn(100, TimeUnit.MILLISECONDS);
//					I in = pool.takeIn();
//					System.out.println(in + " " + running);
					if(in != null) {
						O out = runable.run(in);
						if(out != null) pool.putOut(out);
					}
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void start() {
		running = true;
		thread.start();
	}
	public void stop() {
		running = false;
//		System.out.println("Stopping . . .");
	}
	
	public void printState() {
		System.out.println(thread.getState());
	}
}
