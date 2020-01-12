package game;

public class TypeA extends Tile {
	private TypeA() {
		super(4);
	}

	public TypeA(int x, int y) {
		this();
		this.setPoints(new int[] { x, x, x, x }, new int[] { y, y - 1, y - 2, y - 3 });
	}

	@Override
	protected Tile clone() {
		Tile t = new TypeA();
		for (int i = 0, size = t.getSize(); i < size; i++) {
			t.setX(i, this.getX(i));
			t.setY(i, this.getY(i));
			t.setType(i, this.getType(i));
			t.setDirection(i, this.getDirection(i));
		}
		t.setDirection(this.getDirection());
		return t;
	}

	@Override
	public Tile turn() {
		Tile t = this.clone();
		int x = t.getX(1);
		int y = t.getY(1);
		int direction = t.getDirection();
		t.setDirection((direction + 3) % 2);
		switch (direction) {
		case 0:
			t.setPoints(new int[] { x, x, x, x }, new int[] { y + 1, y, y - 1, y - 2 });
			break;
		case 1:
			t.setPoints(new int[] { x - 1, x, x + 1, x + 2 }, new int[] { y, y, y, y });
			break;
		}
		return t;
	}
}