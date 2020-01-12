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
	protected Tile clone() {
		Tile t = new TypeF();
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
		int x = t.getX(0);
		int y = t.getY(0);
		int direction = t.getDirection();
		t.setDirection((direction + 3) % 4);
		switch (direction) {
		case 0:
			t.setPoints(new int[] { x, x - 1, x + 1, x }, new int[] { y, y, y, y - 1 });
			break;
		case 1:
			t.setPoints(new int[] { x, x, x, x - 1 }, new int[] { y, y + 1, y - 1, y });
			break;
		case 2:
			t.setPoints(new int[] { x, x + 1, x - 1, x }, new int[] { y, y, y, y + 1 });
			break;
		case 3:
			t.setPoints(new int[] { x, x, x, x + 1 }, new int[] { y, y - 1, y + 1, y });
			break;
		}
		return t;
	}
}