package game;

public class TypeD extends Tile {
	private TypeD() {
		super(4);
	}

	public TypeD(int x, int y) {
		this();
		this.setPoints(new int[] { x, x + 1, x, x - 1 }, new int[] { y, y, y - 1, y - 1 });
	}

	@Override
	protected Tile clone() {
		Tile t = new TypeD();
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
		int x = t.getX(2);
		int y = t.getY(2);
		int direction = t.getDirection();
		t.setDirection((direction + 3) % 2);
		switch (direction) {
		case 0:
			t.setPoints(new int[] { x, x + 1, x, x - 1 }, new int[] { y + 1, y + 1, y, y });
			break;
		case 1:
			t.setPoints(new int[] { x + 1, x + 1, x, x }, new int[] { y, y - 1, y, y + 1 });
			break;
		}
		return t;
	}
}