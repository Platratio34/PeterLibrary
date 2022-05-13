package threading;

public interface PoolRunnable<I, O> {
	
	public O run(I data);
}
