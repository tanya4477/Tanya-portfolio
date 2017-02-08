package tanya.deadlock.fix;

public class DeadLockSituation {
	private Object key1 = new Object();
	private Object key2 = new Object();

	public void a() {
		synchronized (key1) {
			System.out.println("[" + Thread.currentThread().getName() + "] I am thread a()");
			b();
		}
	}

	public void b() {
		synchronized (key2) {
			System.out.println("[" + Thread.currentThread().getName() + "] I am thread b()");
			c();
		}
	}

	public void c() {
		synchronized (key1) {
			System.out.println("[" + Thread.currentThread().getName() + "] I am thread c()");
		}
	}
}
