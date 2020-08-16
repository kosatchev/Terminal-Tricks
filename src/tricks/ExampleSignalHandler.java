/*
* ВОПРОСЫ
* Как отключить SignalHandler в конце выполнения метода main?
*/
package tricks;

import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * Signal Handler
 */
public class ExampleSignalHandler {

	public static void main(String... args) throws InterruptedException {
		final long start = System.nanoTime();
		Signal.handle(new Signal("INT"), new SignalHandler() {
			public void handle(Signal sig) {
				System.out.format("\nProgram execution took %f seconds\n", (System.nanoTime() - start) / 1e9f);
				System.out.println("exit command catched");
				//System.exit(0);
			}
		});
		int counter = 30;
		while (counter >= 0) {
			System.out.println(counter--);
			Thread.sleep(500);
		}
	}
}
