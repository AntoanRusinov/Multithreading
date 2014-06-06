package basic.thread.synchronization;

import java.util.Scanner;

class Processor extends Thread {
	// declaring variable as volatile ensures that during the loop
	// the variable will not be changed
	private volatile boolean running = true;

	public void run() {

		while (running) {
			System.out.println("Running");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// Gracefully terminating a thread from another thread
	public void shutdown() {
		running = false;
	}
}

public class VolatileKeywordDemo {

	public static void main(String[] args) {
		Processor proc = new Processor();
		proc.start();

		// Wait for the enter key
		Scanner scanner = new Scanner(System.in);
		String enter = scanner.nextLine();

		System.out.println("Press enter to stop the Thread!");
		if (enter != null) {
			proc.shutdown();
		}
	}
}
