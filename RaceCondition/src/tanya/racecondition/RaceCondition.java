package tanya.racecondition;

public class RaceCondition {
	public static void main(String[] args) throws InterruptedException {
		LongWrapper longWrapper = new LongWrapper(0L);
		Runnable runnable = ()->{
			for (int i = 0; i < 1_000; i++) {
				longWrapper.incrementValue();
			}
		};
		
		Thread[] thread = new Thread[1_000];
		for (int i = 0; i < thread.length; i++) {
			thread[i] = new Thread(runnable);
			thread[i].start();	
		}
		for (int i = 0; i < thread.length; i++) {
			thread[i].join();
		}
		System.out.println("Value : "+longWrapper.getValue());
	}
}
