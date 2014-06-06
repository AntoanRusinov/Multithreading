package wait.and.notify;

import java.util.Scanner;

public class Processor {
	public void produce() throws InterruptedException {
		synchronized (this) {
			System.out.println("Producer thread running ....");
			// because wait is used without time parameters it will wait until
			// other thread say notify! If not notify, this thread can wait
			// forever!
			wait();
			System.out.println("Resumed.");
		}
	}

	public void consume() throws InterruptedException {

		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000);

		synchronized (this) {
			System.out.println("Waiting for return key.");
			scanner.nextLine();
			System.out.println("Return key pressed.");
			// when notify actually allows to exit the
			// synch. block and unlock the object
			notify();
			Thread.sleep(5000);
		}
	}
}
