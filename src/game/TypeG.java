package game;

public class TypeG extends Tile {
	private TypeG() {
		super((byte) 4);
	}

	public TypeG(int x, int y) {
		this();
		this.setPoints(new int[] { x, x + 1, x, x + 1 }, new int[] { y, y, y - 1, y - 1 });
	}

	@Override
	protected Tile clone() {
		Tile t = new TypeG();
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
		return this.clone();
	}
}