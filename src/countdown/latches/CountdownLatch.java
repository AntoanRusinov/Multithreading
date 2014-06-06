package countdown.latches;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable {
	private CountDownLatch latch;

	public Processor(CountDownLatch latch) {
		this.latch = latch;
	}

	public void run() {
		System.out.println("Started.");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		latch.countDown();
	}
}

public class CountdownLatch {

	public static void main(String[] args) {

		CountDownLatch latch = new CountDownLatch(3);

		// this will create three Threads
		ExecutorService executor = Executors.newFixedThreadPool(3);

		// for each thread will pass one Processor
		for (int i = 0; i < 3; i++) {
			executor.submit(new Processor(latch));
		}

		try {
			// waits until the CountDownLatch is zero
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Completed.");
	}
}
