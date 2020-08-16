package tricks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Getting actual tty resolution. Works on Linux
 * on Windows returns default values
 * from jexer
 */
public class GetTerminalSize {

	/**
	 * Text window width. Default is 80x24 (same as VT100 terminals).
	 */
	private int windowWidth = 80;

	/**
	 * Text window height. Default is 80x24 (same as VT100 terminals).
	 */
	private int windowHeight = 24;

	private void sttyWindowSize() {
		String[] cmd = {
			"/bin/sh", "-c", "stty size < /dev/tty"
		};
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(process.getInputStream(), "UTF-8"));
			String line = in.readLine();
			if ((line != null) && (line.length() > 0)) {
				StringTokenizer tokenizer = new StringTokenizer(line);
				int rc = Integer.parseInt(tokenizer.nextToken());
				if (rc > 0) {
					windowHeight = rc;
				}
				rc = Integer.parseInt(tokenizer.nextToken());
				if (rc > 0) {
					windowWidth = rc;
				}
			}
			while (true) {
				BufferedReader err = new BufferedReader(
						new InputStreamReader(process.getErrorStream(),
								"UTF-8"));
				line = err.readLine();
				if ((line != null) && (line.length() > 0)) {
					System.err.println("Error output from stty: " + line);
				}
				try {
					process.waitFor();
					break;
				} catch (InterruptedException e) {
					// SQUASH
				}
			}
			int rc = process.exitValue();
			if (rc != 0) {
				System.err.println("stty returned error code: " + rc);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		GetTerminalSize s = new GetTerminalSize();
		System.out.println("Default settings:");
		System.out.println("w = " + s.windowWidth + "; h = " + s.windowHeight);
		long start = System.currentTimeMillis();
		s.sttyWindowSize();
		long end = System.currentTimeMillis();
		System.out.println("Current settings:");
		System.out.println("w = " + s.windowWidth + "; h = " + s.windowHeight);
		System.out.println("Calculating time: " + (end - start) + " mills");
	}
}
