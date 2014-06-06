package synch.keyword;

public class Worker {

	private int count = 0;

	// when the method is locked by thread1, thread2 can not access it
	// thread2 must wait until thread1 unlock!
	public synchronized void increment() {
		count++;
	}

	public static void main(String[] args) {
		Worker worker = new Worker();
		worker.run();
	}

	public void run() {
		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10000; i++) {
					increment();
				}
			}
		});
		thread1.start();

		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10000; i++) {
					increment();
				}
			}
		});
		thread2.start();

		try {
			// join() ,it makes sure that as soon as a thread calls
			// join,the current thread(yes,currently running thread) will
			// not execute unless the thread you have called join is finished.
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Count is: " + count);
	}
}
