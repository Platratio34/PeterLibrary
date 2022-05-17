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
	public I pollIn(long timeout, TimeUnit unit) throws InterruptedException {
		return inputs.poll(timeout, unit);
//		return null;
	}
	public void putIn(I data) throws InterruptedException {
		inputs.put(data);
	}
	
	public void putOut(O data) throws InterruptedException {
		outputs.put(data);
	}
	public O pollOut(long timeout, TimeUnit unit) throws InterruptedException {
		return outputs.poll(timeout, unit);
//		return null;
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
	
	public void printThreadStates() {
		for(PoolThread<I, O> t : pool) {
			t.printState();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Starting thread pool");
		ThreadPool<Integer, Integer> pool = new ThreadPool<Integer, Integer>(16, new PoolRunnable<Integer, Integer>() {
			@Override
			public Integer run(Integer data) {
//				System.out.println("Started input " + data);
				int i = 0;
				while(i < 0xffff) {
					i++;
				}
//				System.out.println("Finished input " + data);
				return data;
			}
		});
		System.out.println("Pool started");
		int nTries = 1000000;
		
		float avgErr = 0;
		float avgMaxErr = 0;
		float avgTime = 0;
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < 20; i++) {
			long sTime = System.currentTimeMillis();
			Thread tI = new Thread(() -> {
//				System.out.println("Input thread started");
				for(int j = 0; j < nTries; j++) {
					try {
						pool.putIn(j);
//						System.out.println("Input " + i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
//				System.out.println("Inputs done");
			});
			tI.start();
			
			int n = 0;
			int max = -1;
			int err = 0;
			int maxErr = -1;
			while(n < nTries) {
				try {
					int data = pool.takeOut();
					@SuppressWarnings("unused")
					String msg = data+"";
					if(data < max) {
						msg += " - " + (max-data);
						maxErr = Math.max(maxErr, (max-data));
						err++;
					}
					if(data > max && (data-max) > 1) {
						maxErr = Math.max(maxErr, (data-max));
						msg += " + " + (data-max);
					}
//					System.out.println(n+":\t" + msg);
					max = Math.max(max, data);
					n++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Trial " + i);
			System.out.println("\tError: " + ( ( (float)err/(float)nTries ) * 100f ) + "%");
			System.out.println("\tMax Error: " + maxErr);
			long time = System.currentTimeMillis() - sTime;
			System.out.println("\tTime: " + time + "ms");
			if(avgErr == 0) avgErr = (float)err/(float)nTries;
			avgErr += (float)err/(float)nTries;
			avgErr /= 2f;
			if(avgMaxErr == 0) avgErr = maxErr;
			avgMaxErr += maxErr;
			avgMaxErr /= 2f;
			if(avgTime == 0) avgTime = time;
			avgTime += time;
			avgTime /= 2f;
		}
		pool.stopAllThreads();
		System.out.println("\nAverage Error: " + ( avgErr * 100f ) + "%");
		System.out.println("Average Max Error: " + avgMaxErr);
		System.out.println("Average Time: " + avgTime + "ms");
		System.out.println("Total time: " + (System.currentTimeMillis() - startTime) + "ms");
		try {
			Thread.sleep(300);
		} catch(Exception e) {
			
		}
//		pool.printThreadStates();
	}
}
