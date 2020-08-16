package tricks;

import java.util.Scanner;

/**
 * FullScreenAnimation demos. Linux Only
 *
 * @author kosatchev
 */
public class FullScreenAnimation {

	/**
	 * Example method
	 *
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		while (true) {
			System.out.print(
					"--------MENU--------\n"
					+ "3. Full screen animation Top\n"
					+ "4. Full screen animation Bottom\n"
					+ "q. Exit\n"
					+ "Enter your choice: ");
			Scanner sc = new Scanner(System.in);
			String select = sc.nextLine();
			System.out.println("--------------------\n");
			if (select.equals("3")) {
				FullScreenAnimation fsa = new FullScreenAnimation();
				fsa.animateScreenTop();
			} else if (select.equals("4")) {
				FullScreenAnimation fsa = new FullScreenAnimation();
				fsa.animateScreenBottom();
			} else if (select.equals("q")) {
				System.out.println("exiting\n");
				break;
			}
			System.out.println();
		}
	}

	private static final String ANSI_CLS_TOP = "\033[H\033[2J";
	private static final String ANSI_CLS_BOTTOM = "\u001b[2J";

	public void animateScreenTop() throws InterruptedException {
		System.out.print("\033[?1003;1005h\033[?1049h"); //вход в режим

		for (int i = 9; i >= 0; i--) {
			System.out.print(ANSI_CLS_TOP); // Top
			System.out.flush(); // Flush screen
			for (int j = 0; j < 10; j++) {
				System.out.println(i);
			}
			Thread.sleep(300);
		}
		System.out.print("\033[?1003;1005l\033[?1049l"); //выход из режима
	}

	public void animateScreenBottom() throws InterruptedException {
		System.out.print("\033[?1003;1005h\033[?1049h"); //вход в режим
//			System.out.flush(); // Flush screen before animation (only in Bottom mode after Top)

		for (int i = 9; i >= 0; i--) {
			System.out.print(ANSI_CLS_BOTTOM); // Bottom
			System.out.flush(); // Flush screen
			for (int j = 0; j < 10; j++) {
				System.out.println(i);
			}
			Thread.sleep(300);
		}
		System.out.print("\033[?1003;1005l\033[?1049l"); //выход из режима
	}
}
