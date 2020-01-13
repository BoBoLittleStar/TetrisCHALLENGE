package game;

public class TypeE extends Tile {
	private int direction;

	private TypeE() {
		super(4);
		this.direction = 1;
	}

	public TypeE(int x, int y) {
		this();
		this.setPoints(new int[] { x, x - 1, x, x + 1 }, new int[] { y, y, y - 1, y - 1 });
	}

	@Override
	protected TypeE clone() {
		TypeE t = new TypeE();
		for (int i = 0, size = t.getSize(); i < size; i++) {
			t.setX(i, this.getX(i));
			t.setY(i, this.getY(i));
			t.setType(i, this.getType(i));
			t.setDirection(i, this.getDirection(i));
			t.direction = this.direction;
		}
		return t;
	}

	@Override
	public TypeE turn() {
		TypeE t = this.clone();
		t.direction = -this.direction;
		int x = t.getX(2);
		int y = t.getY(2);
		int x_ = t.getX(0);
		int y_ = t.getY(0);
		t.setX(0, -(y_ - y) * this.direction + x);
		t.setY(0, (x_ - x) * this.direction + y);
		x_ = t.getX(1);
		y_ = t.getY(1);
		t.setX(1, -(y_ - y) * this.direction + x);
		t.setY(1, (x_ - x) * this.direction + y);
		x_ = t.getX(3);
		y_ = t.getY(3);
		t.setX(3, -(y_ - y) * this.direction + x);
		t.setY(3, (x_ - x) * this.direction + y);
		return t;
	}
}