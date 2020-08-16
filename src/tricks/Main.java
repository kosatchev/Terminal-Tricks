package tricks;

import java.util.Scanner;

/**
 * Terminal tricks compilation
 * @author kosatchev
 */
public class Main {

	public static void printMenu() {
		System.out.print(
				"--------MENU--------\n"
				+ "1. Get terminal size\n"
				+ "2. Single line animation\n"
				+ "3. Full screen animation Top\n"
				+ "4. Full screen animation Bottom\n"
				+ "5. Keyboard input\n"
				+ "6. Disable Ctrl-C\n"
				+ "7. Shutdown hook\n"
				+ "q. Exit\n"
				+ "Enter your choice: ");
	}

	public static void main(String[] args) throws InterruptedException {
		while (true) {
			//KeyboardInput kb = new KeyboardInput();
			printMenu();
			Scanner sc = new Scanner(System.in);
			String select = sc.nextLine();
			System.out.println("--------------------\n");
			if (select.equals("1")) {
				GetTerminalSize.main(args);
				
			} else if (select.equals("2")) {
				SingleLineAnimation animation = new SingleLineAnimation();
				// Animation
				SingleLineAnimation.Spinner spinner = animation.new Spinner();
				int totalFrames1 = 72;
				for (int i = 1; i <= totalFrames1; i++) {
					// Percent animation step
					String percent = String.format("%5.1f", (float) i / totalFrames1 * 100) + "%";
					// Progressbar animation step
					String progress = animation.progressBar(i, totalFrames1, 40, '#', ' ', percent);
					// Full animation
					animation.animateLine(spinner.spin() + progress);
					Thread.sleep(150);
				}
				System.out.println();

			} else if (select.equals("3")) {
				FullScreenAnimation fsa = new FullScreenAnimation();
				fsa.animateScreenTop();
				
			} else if (select.equals("4")) {
				FullScreenAnimation fsa = new FullScreenAnimation();
				fsa.animateScreenBottom();
				
			} else if (select.equals("5")) {
				KeyboardInput.main(args);
				
			} else if (select.equals("6")) {
				ExampleSignalHandler.main(args);
				
			} else if (select.equals("7")) {
				ShutdownHook.main(args);
				
			} else if (select.equals("q")) {
				System.out.println("exiting\n");
				break;
			}
			System.out.println();
		}
	}
}
