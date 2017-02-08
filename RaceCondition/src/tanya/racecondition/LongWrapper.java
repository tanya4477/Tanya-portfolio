package tanya.racecondition;

public class LongWrapper {
	private long l;
	private Object key = new Object();

	public LongWrapper(Long l) {
		this.l = l;
	}

	public long getValue() {
		synchronized (key) {
			return l;
		}
	}
//happens before link between incrementValue() and getValue()
	public void incrementValue() {
		synchronized (key) {
			l = l + 1;
		}
	}
}
