/*
* ВОПРОСЫ
* 1. Как правильно считывать нажатия клавиш? Например, F1 возвращает 3 бита.
* 2. Как в терминале считывать нажатия и координаты и мыши?
*/
package tricks;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Keyboard input demo during the main loop
 */
public class KeyboardInput {

	private static String ttyConfig;

	public static void main(String[] args) {

		System.out.println("press any key to capture it's keyCode\n"
				+ "--------------\n"
				+ "CTRL+D to exit\n"
				+ "--------------");
		
		
		try {
			setTerminalToCBreak();

			int i = 0;
			while (true) {

				StringBuilder sb = new StringBuilder();
				sb.append(i++); // число, до которого досчитал цикл

				//     System.out.println( ""+ i++ ); // Метод, который выполняется на экране
				if (System.in.available() > 0) {
					int c = System.in.read();
					sb.append(" - ");
					sb.append(c);// Число, полученное с ридера
					sb.append(" - ");
					sb.append(String.format("0x%08X", c));// шеснадцатиричный вид кодов

					System.out.println(sb.toString());
					if (c == 0x04) { //ctrl-d to exit
						break;
					}
				}
//System.out.println(sb.toString()); // вывод строки на экран
			} // end while
		} catch (IOException e) {
			System.err.println("IOException");
		} catch (InterruptedException e) {
			System.err.println("InterruptedException");
		} finally {
			try {
				stty(ttyConfig.trim());
			} catch (Exception e) {
				System.err.println("Exception restoring tty config");
			}
		}

	}

	private static void setTerminalToCBreak() throws IOException, InterruptedException {

		ttyConfig = stty("-g");

		// set the console to be character-buffered instead of line-buffered
		stty("-icanon min 1");

		// disable character echoing
		stty("-echo");
	}

	/**
	 * Execute the stty command with the specified arguments against the current
	 * active terminal.
	 */
	private static String stty(final String args)
			throws IOException, InterruptedException {
		String cmd = "stty " + args + " < /dev/tty";
		//String cmd = "stty " + "-ignbrk -brkint -parmrk -istrip -inlcr -igncr -icrnl -ixon -opost -echo -echonl -icanon -isig -iexten -parenb cs8 min 1" + " < /dev/tty";
		// Строка из jexer. разобрать что к чему. Вообще, ей здесь не место и вызывается метод выше
		return exec(new String[]{
			"sh",
			"-c",
			cmd
		});
	}

	/**
	 * Execute the specified command and return the output (both stdout and
	 * stderr).
	 */
	private static String exec(final String[] cmd)
			throws IOException, InterruptedException {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();

		Process p = Runtime.getRuntime().exec(cmd);
		int c;
		InputStream in = p.getInputStream();

		while ((c = in.read()) != -1) {
			bout.write(c);
		}

		in = p.getErrorStream();

		while ((c = in.read()) != -1) {
			bout.write(c);
		}

		p.waitFor();

		String result = new String(bout.toByteArray());
		return result;
	}

}
