package global;

public class Global {
	private static final int[] colors = new int[] { 0xffffff, 0xff0000, 0x00ff00, 0x0000ff, 0x7f7f7f };

	public static final int getColor(int color) {
		return colors[color];
	}
}