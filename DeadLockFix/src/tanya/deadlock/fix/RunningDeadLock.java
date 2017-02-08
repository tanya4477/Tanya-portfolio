package tanya.deadlock.fix;

public class RunningDeadLock {
	public static void main(String[] args) throws InterruptedException{
		DeadLockSituation deadLock = new DeadLockSituation();

		Runnable runnable1 = () -> {
			deadLock.a();
		};
		Runnable runnable2 = () -> {
			deadLock.b();
		};

		Thread t1 = new Thread(runnable1);
		Thread t2 = new Thread(runnable2);

		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}
}
