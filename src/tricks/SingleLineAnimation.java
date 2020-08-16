package tricks;

/**
 * Draws single line animation in terminal
 *
 * @author kosatchev
 */
public class SingleLineAnimation {

	private String lastLine = "";

	/**
	 * Example method
	 *
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		SingleLineAnimation animation = new SingleLineAnimation();
		// Animation
		Spinner spinner = animation.new Spinner();
		int totalFrames1 = 72;
		for (int i = 1; i <= totalFrames1; i++) {
			// Percent animation step
			String percent = String.format("%5.1f", (float) i / totalFrames1 * 100) + "%";
			// Progressbar animation step
			String progress = animation.progressBar(i, totalFrames1, 20, '#', ' ', percent);
			// Full animation
			animation.animateLine(spinner.spin() + progress);
			Thread.sleep(150);
		}
		System.out.println();
	}

	/**
	 * Prints animation frame
	 *
	 * @param line - String to be animated
	 */
	public void animateLine(String line) {
		// Clear the last line if longer
		if (lastLine.length() > line.length()) {
			String temp = "";
			for (int i = 0; i < lastLine.length(); i++) {
				temp += " ";
			}
			if (temp.length() > 1) {
				System.out.print("\r" + temp);
			}
		}
		System.out.print("\r" + line);
		lastLine = line;
	}

	/**
	 * Draws single frame of progress bar animation
	 *
	 * @param step current animation step
	 * @param total total steps
	 * @param length length of progress bar (in chars)
	 * @param filler filled progress bar
	 * @param empty empty progress bar
	 * @param divider string between filled and empty progress bar
	 * @return String of animation frame
	 */
	public String progressBar(int step, int total, int length, char filler, char empty, String divider) {
		String rsl = "";
		String passedMultiplier = Integer.toString(Math.round((float) step / total * length));
		if (passedMultiplier.equals("0")) {
			passedMultiplier = "";
		}
		String leftMultiplier = Integer.toString(length - Math.round((float) step / total * length));
		if (leftMultiplier.equals("0")) {
			leftMultiplier = "";
		}
		String passed = String.format("%" + passedMultiplier + "s", "").replace(' ', filler);
		String left = String.format("%" + leftMultiplier + "s", "").replace(' ', empty);
		rsl = "[" + passed + divider + left + "]";
		return rsl;
	}

	/**
	 * Spinner [ / ]
	 */
	public class Spinner {

		String[] frames = {"\\", "|", "/", "-"};
		int currentFrame = 0;

		/**
		 * Spinner animation single step
		 *
		 * @return String of animation frame
		 */
		public String spin() {
			String rsl = "";
			if (currentFrame == frames.length) {
				currentFrame = 0;
			}
			rsl = "[ " + frames[currentFrame] + " ]";
			currentFrame += 1;
			return rsl;
		}
	}
}
