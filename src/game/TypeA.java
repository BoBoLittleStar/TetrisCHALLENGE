package game;

public class TypeA extends Tile {
	private int direction;

	private TypeA() {
		super(4);
		this.direction = 1;
	}

	public TypeA(int x, int y) {
		this();
		this.setPoints(new int[] { x, x, x, x }, new int[] { y, y - 1, y - 2, y - 3 });
	}

	@Override
	protected TypeA clone() {
		TypeA t = new TypeA();
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
	public TypeA turn() {
		TypeA t = this.clone();
		t.direction = -this.direction;
		int x = t.getX(1);
		int y = t.getY(1);
		int x_ = t.getX(0);
		int y_ = t.getY(0);
		t.setX(0, -(y_ - y) * this.direction + x);
		t.setY(0, (x_ - x) * this.direction + y);
		x_ = t.getX(2);
		y_ = t.getY(2);
		t.setX(2, -(y_ - y) * this.direction + x);
		t.setY(2, (x_ - x) * this.direction + y);
		x_ = t.getX(3);
		y_ = t.getY(3);
		t.setX(3, -(y_ - y) * this.direction + x);
		t.setY(3, (x_ - x) * this.direction + y);
		return t;
	}
}