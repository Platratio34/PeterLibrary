package threading;

public class Job implements Runnable {
	
	private int jobId;
	private int batchId;
	public Object output;
	private JobRunnable runnable;
	
	public Job(int id, JobRunnable runnable) {
		jobId = id;
		batchId = -1;
		this.runnable = runnable;
	}
	public Job(int jobId, int batchId, JobRunnable runnable) {
		this.jobId = jobId;
		this.batchId = batchId;
		this.runnable = runnable;
	}
	
	@Override
	public void run() {
		runnable.run(this);
	}
	
	public int getJobId() {
		return jobId;
	}
	public int getBatchId() {
		return batchId;
	}
	
	public interface JobRunnable {
		public void run(Job job);
	}
}
