package game;

public class TypeBomb extends Tile {
	private TypeBomb() {
		super(1);
	}

	public TypeBomb(int x, int y) {
		this();
		this.setPoints(new int[] { x }, new int[] { y });
		this.setType(0, 4);
	}

	@Override
	protected Tile clone() {
		Tile t = new TypeBomb();
		t.setX(0, this.getX(0));
		t.setY(0, this.getY(0));
		t.setType(0, this.getType(0));
		t.setDirection(0, this.getDirection(0));
		return t;
	}

	@Override
	public Tile turn() {
		return this.clone();
	}
}