package game;

public abstract class Tile {
	private int size;
	private int[] bricks;

	protected Tile(int size) {
		this.size = size;
		this.bricks = new int[size];
	}

	@Override
	protected abstract Tile clone();

	public Tile turn(int direction) {
		Tile t = this.clone();
		for (int i = 0; i < direction % 4; i++)
			t = t.turn();
		return t;
	}

	public Tile moveLeft() {
		Tile t = this.clone();
		for (int i = 0; i < t.size; i++)
			t.setX(i, this.getX(i) - 1);
		return t;
	}

	public Tile moveRight() {
		Tile t = this.clone();
		for (int i = 0; i < t.size; i++)
			t.setX(i, this.getX(i) + 1);
		return t;
	}

	public Tile moveDown() {
		Tile t = this.clone();
		for (int i = 0; i < t.size; i++)
			t.setY(i, this.getY(i) + 1);
		return t;
	}

	public abstract Tile turn();

	protected void setPoints(int[] x, int[] y) {
		for (int i = 0; i < this.size; i++) {
			this.setX(i, x[i]);
			this.setY(i, y[i]);
		}
	}

	public int getSize() {
		return this.size;
	}

	protected void setX(int i, int x) {
		this.bricks[i] = this.bricks[i] & 0xffff00ff | x << 8;
	}

	protected void setY(int i, int y) {
		this.bricks[i] = this.bricks[i] & 0xffffff00 | y;
	}

	protected void setType(int i, int type) {
		this.bricks[i] = this.bricks[i] & 0xff00ffff | type << 16;
	}

	protected void setDirection(int i, int direction) {
		this.bricks[i] = this.bricks[i] & 0xffffff | direction << 24;
	}

	public int getX(int i) {
		return (this.bricks[i] & 0xff00) >> 8;
	}

	public int getY(int i) {
		return this.bricks[i] & 0xff;
	}

	public int getType(int i) {
		return (this.bricks[i] & 0xff0000) >>> 16;
	}

	public int getDirection(int i) {
		return (this.bricks[i] & 0xff000000) >>> 24;
	}
}