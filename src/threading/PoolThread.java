package threading;

public class PoolThread<I, O> {
	
	private Thread thread;
	private boolean running = false;
	
	public PoolThread(ThreadPool<I, O> pool, PoolRunnable<I, O> runable) {
		thread = new Thread(() -> {
			while(running) {
				try {
//					System.out.println("waiting");
					pool.putOut(runable.run(pool.takeIn()));
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
	}
}
