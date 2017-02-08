package tanya.producerconsumer.solution;

import java.util.Deque;
import java.util.LinkedList;

import tanya.producerconsumer.solution.ProducerConsumerArrayList.Producer.Consumer;

public class ProducerConsumerArrayList {
	private static Object lock = new Object();
	private static Deque<Integer> buffer;
	private static final int MAX_SIZE = 200;

	static class Producer {
		void produce() {
			synchronized (lock) {
				try {
					if (buffer.size() == MAX_SIZE) {

						System.out.println("producer is waiting because buffer is full");
						lock.wait(10);
					}
					else
						lock.wait(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				buffer.add(1);
				System.out.println("Producing--Buffer size" + buffer.size() + " =" + buffer);
				lock.notify();
			}
		}// class pro

		static class Consumer {
			void consume() {
				synchronized (lock) {
					try {
						if (buffer.size() == 0) {
							System.out.println("consumer is waiting because buffer is empty");
							lock.wait(10);
						}
						else
						lock.wait(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					buffer.pop();
					System.out.println("Consuming--Buffer size" + buffer.size() + " =" + buffer);
					lock.notify();
				}
			}
		}// class con

		static boolean isEmpty(Deque<Integer> buffer) {
			return buffer.isEmpty();
		}// isEmpty

		static boolean isFull(Deque<Integer> buffer) {
			return MAX_SIZE == buffer.size();
		}// isFull
	}

	public static void main(String... strings) throws InterruptedException {
		buffer = new LinkedList<>();
		Producer producer = new Producer();
		Consumer consumer = new Consumer();

		Runnable produceTask = () -> {
			int count = 0;
			while (count++ < MAX_SIZE) {
				producer.produce();
			}
			System.out.println("Done producing");
		};
		Runnable consumeTask = () -> {
			int count = 0;
			while (count++ < MAX_SIZE) {
				consumer.consume();
			}
			System.out.println("Done consuming");
		};

		Thread producerThread = new Thread(produceTask);
		Thread consumerThread = new Thread(consumeTask);

		producerThread.start();
		consumerThread.start();

		// producerThread.join(); consumerThread.join();

	}
}
