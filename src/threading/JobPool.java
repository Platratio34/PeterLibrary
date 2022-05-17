package threading;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import threading.Job.JobRunnable;

public class JobPool {
	
	private BlockingQueue<Job> jobs;
	private BlockingQueue<Job> doneJobs;
	private HashMap<Integer, Job> doneJobMap;
	private HashMap<Integer, ArrayList<Job>> doneJobMapBatch;
	private Thread[] threads;
	private boolean running;
	private int maxId = 0;
	
	public JobPool(int numThreads) {
		jobs = new LinkedBlockingQueue<Job>();
		doneJobs = new LinkedBlockingQueue<Job>();
		doneJobMap = new HashMap<Integer, Job>();
		doneJobMapBatch = new HashMap<Integer, ArrayList<Job>>();
		threads = new Thread[numThreads];
		running = true;
		for(int i = 0; i < numThreads; i++) {
			threads[i] = new Thread(() -> {
				while(isRunning()) {
					try {
						Job job = jobs.poll(100, TimeUnit.MILLISECONDS);
						if(job != null) {
							job.run();
							doneJobs.put(job);
						}
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			threads[i].start();
		}
	}
	
	public boolean isRunning() {
		return running;
	}
	public void stop() {
		running = false;
	}
	
	public void pullDone() {
		ArrayList<Job> jbs = new ArrayList<Job>();
		doneJobs.drainTo(jbs);
//		System.out.println(jbs.size() + " jobs done");
//		System.out.println(jobs.size() + " jobs waiting");
		for(Job j : jbs) {
			doneJobMap.put(j.getJobId(), j);
			int b = j.getBatchId();
			if(b != -1) {
				if(!doneJobMapBatch.containsKey(b)) {
					doneJobMapBatch.put(b, new ArrayList<Job>());
				}
				doneJobMapBatch.get(b).add(j);
			}
		}
	}
	
	public Job getJobById(int id) {
		pullDone();
		if(doneJobMap.containsKey(id)) {
			Job j = doneJobMap.get(id);
			doneJobMap.remove(id);
			return j;
		}
		return null;
	}
	public ArrayList<Job> getJobsByBatch(int id) {
		pullDone();
		if(doneJobMapBatch.containsKey(id)) {
			ArrayList<Job> j = doneJobMapBatch.get(id);
			doneJobMapBatch.remove(id);
			return j;
		}
		return null;
	}
	
	public boolean hasJobById(int id) {
		pullDone();
		return doneJobMap.containsKey(id);
	}
	public boolean hasJobByBatch(int id) {
		pullDone();
		return doneJobMapBatch.containsKey(id) && !doneJobMapBatch.get(id).isEmpty();
	}
	
	public int newJob(JobRunnable runnable) {
		maxId++;
		jobs.add(new Job(maxId, runnable));
		return maxId;
	}
	public int newJobBatch(int batchId, JobRunnable runnable) {
		maxId++;
		jobs.add(new Job(maxId, batchId, runnable));
		return maxId;
	}
	
	public static void main(String[] args) {
		JobPool pool = new JobPool(8);
		int totalJobs = 1000;
		Thread t = new Thread(() -> {
			System.out.println("Starting jobs");
			for(int i = 0; i < totalJobs; i++) {
				int in = i;
				pool.newJobBatch(1, (Job job) -> {
					for(int j = 0; j < 1000; j++) {
						job.output = ""+j;
					}
					job.output = "Job " + in + " done";
				});
			}
			System.out.println("Done starting jobs");
		});
		t.start();
		int done = 0;
		while(done < totalJobs) {
			ArrayList<Job> jbs = pool.getJobsByBatch(1);
			if(jbs != null) {
				System.out.println("Got " + jbs.size() + " jobs");
				for(Job j : jbs) {
					System.out.println("| " + j.output);
					done++;
				}
			} else {
				System.out.println("No jobs waiting");
			}
		}
		pool.stop();
	}
}
