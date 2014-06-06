package reentrant.lock;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	private int count = 0;
	private Lock lock = new ReentrantLock();
	//getting the condition object from the lock
	private Condition cond = lock.newCondition();

	private void increment() {
		for (int i = 0; i < 10000; i++) {
			count++;
		}
	}

	public void firstThread() throws InterruptedException {
		lock.lock();

		System.out.println("Waiting ....");
		cond.await();

		System.out.println("Woken up!");

		// we use try-catch because if increment method
		// throws an ex. the object will not be unlocked!
		try {
			increment();
		} catch (Exception e) {
			// properly handle the exception
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void secondThread() throws InterruptedException {

		Thread.sleep(1000);
		lock.lock();

		System.out.println("Press the return key!");
		new Scanner(System.in).nextLine();
		System.out.println("Got return key!");

		cond.signal();

		try {
			increment();
		} catch (Exception e) {
			// properly handle the exception
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void finished() {
		System.out.println("Count is: " + count);
	}
}
