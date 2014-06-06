package starting.threads;

public class ThreadDemo {

	public static void main(String[] args) {
		// starting a Thread a little bit tricky
		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 20; i++) {
					System.out.println("Hello: " + i);

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		});
		// calling start() method actually tells jvm
		// to search run() method and execute it
		thread1.start();
	}

}
