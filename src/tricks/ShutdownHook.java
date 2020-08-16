package tricks;

/**
 *Shutdown Hook
 */
public class ShutdownHook {

	public static void main(String... args) throws InterruptedException {
		final long start = System.nanoTime();
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				System.out.format("\nProgram execution took %f seconds\n", (System.nanoTime() - start) / 1e9f);
				System.out.println("doing something and exit");
			}
		}));
		int counter = 30;
		while (counter >= 0) {
			System.out.println(counter--);
			Thread.sleep(500);
		}
	}
}
