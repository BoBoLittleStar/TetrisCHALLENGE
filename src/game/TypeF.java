package game;

public class TypeF extends Tile {
	private TypeF() {
		super(4);
	}

	public TypeF(int x, int y) {
		this();
		this.setPoints(new int[] { x, x - 1, x + 1, x }, new int[] { y, y, y, y - 1 });
	}

	@Override
	protected TypeF clone() {
		TypeF t = new TypeF();
		for (int i = 0, size = t.getSize(); i < size; i++) {
			t.setX(i, this.getX(i));
			t.setY(i, this.getY(i));
			t.setType(i, this.getType(i));
			t.setDirection(i, this.getDirection(i));
		}
		return t;
	}

	@Override
	public TypeF turn() {
		TypeF t = this.clone();
		int x = t.getX(0);
		int y = t.getY(0);
		int x_ = t.getX(1);
		int y_ = t.getY(1);
		t.setX(1, -(y_ - y) + x);
		t.setY(1, x_ - x + y);
		x_ = t.getX(2);
		y_ = t.getY(2);
		t.setX(2, -(y_ - y) + x);
		t.setY(2, x_ - x + y);
		x_ = t.getX(3);
		y_ = t.getY(3);
		t.setX(3, -(y_ - y) + x);
		t.setY(3, x_ - x + y);
		return t;
	}
}