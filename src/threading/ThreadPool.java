package threading;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class ThreadPool<I ,O> {
	
	private ArrayList<PoolThread<I ,O>> pool;
	
	private BlockingQueue<I> inputs;
	private BlockingQueue<O> outputs;
	
	public ThreadPool(int threads, PoolRunnable<I, O> runnable) {
		pool = new ArrayList<PoolThread<I, O>>();
		inputs = new LinkedBlockingDeque<I>();
		outputs = new LinkedBlockingDeque<O>();
		
		for(int i = 0; i < threads; i++) {
			PoolThread<I, O> t = new PoolThread<I ,O>(this, runnable);
			pool.add(t);
			t.start();
		}
	}
	
	public I takeIn() throws InterruptedException {
		return inputs.take();
	}
	public void putIn(I data) throws InterruptedException {
		inputs.put(data);
	}
	
	public void putOut(O data) throws InterruptedException {
		outputs.put(data);
	}
	public O pollOut(long timeout, TimeUnit unit) throws InterruptedException {
		outputs.poll(timeout, unit);
		return null;
	}
	public O takeOut() throws InterruptedException {
		return outputs.take();
	}
	public boolean hasOut() {
		return !outputs.isEmpty();
	}
	
	public void stopAllThreads() {
		for(PoolThread<I, O> t : pool) {
			t.stop();
		}
	}
	
	public static void main(String[] args) {
		ThreadPool<Integer, String> pool = new ThreadPool<Integer, String>(16, new PoolRunnable<Integer, String>() {
			@Override
			public String run(Integer data) {
//				System.out.println("Started input " + data);
				int in = (int)data;
				while(in < 500) {
					in++;
				}
//				System.out.println("Finished input " + data);
				return "Now " + (int)data;
			}
		});
		System.out.println("Pool started");
		Thread tI = new Thread(() -> {
//			System.out.println("Input thread started");
			for(int i = 0; i < 100; i++) {
				try {
					pool.putIn(i);
//					System.out.println("Input " + i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Inputs done");
		});
		tI.start();
		while(true) {
			try {
				System.out.println(pool.takeOut());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
