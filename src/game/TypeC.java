package game;

public class TypeC extends Tile {
	private TypeC() {
		super(4);
	}

	public TypeC(int x, int y) {
		this();
		this.setPoints(new int[] { x, x - 1, x, x }, new int[] { y, y, y - 1, y - 2 });
	}

	@Override
	protected TypeC clone() {
		TypeC t = new TypeC();
		for (int i = 0, size = t.getSize(); i < size; i++) {
			t.setX(i, this.getX(i));
			t.setY(i, this.getY(i));
			t.setType(i, this.getType(i));
			t.setDirection(i, this.getDirection(i));
		}
		return t;
	}

	@Override
	public TypeC turn() {
		TypeC t = this.clone();
		int x = t.getX(2);
		int y = t.getY(2);
		int x_ = t.getX(0);
		int y_ = t.getY(0);
		t.setX(0, -(y_ - y) + x);
		t.setY(0, x_ - x + y);
		x_ = t.getX(1);
		y_ = t.getY(1);
		t.setX(1, -(y_ - y) + x);
		t.setY(1, x_ - x + y);
		x_ = t.getX(3);
		y_ = t.getY(3);
		t.setX(3, -(y_ - y) + x);
		t.setY(3, x_ - x + y);
		return t;
	}
}