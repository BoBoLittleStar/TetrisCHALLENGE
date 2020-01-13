package global;

public class Global {
	private static final int[] colors = new int[] { 0xffffff, 0xff0000, 0x00ff00, 0x0000ff };

	public static final int getColor(int color) {
		return color >= colors.length ? 0x0 : colors[color];
	}
}