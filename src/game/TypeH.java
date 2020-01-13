package game;

public class TypeH extends Tile {
	private TypeH() {
		super(4);
	}

	public TypeH(int x, int y) {
		this();
		this.setPoints(new int[] { x, x, x + 1, x + 1 }, new int[] { y, y - 1, y - 1, y });
		int i = (int) (Math.random() * 4);
		this.setType(1, i);
		this.setType(2, i);
		i = (int) (Math.random() * 4);
		this.setType(0, i);
		this.setType(3, i);
		this.setDirection(1, 1);
		this.setDirection(2, 2);
		i = (int) (Math.random() * 2);
		this.setDirection(0, i == 0 ? 0 : 3);
		this.setDirection(3, i == 0 ? 3 : 0);
	}

	@Override
	protected TypeH clone() {
		TypeH t = new TypeH();
		for (int i = 0, size = t.getSize(); i < size; i++) {
			t.setX(i, this.getX(i));
			t.setY(i, this.getY(i));
			t.setType(i, this.getType(i));
			t.setDirection(i, this.getDirection(i));
		}
		return t;
	}

	@Override
	public TypeH turn() {
		TypeH t = this.clone();
		int temp = t.getType(0);
		t.setType(0, t.getType(3));
		t.setType(3, t.getType(2));
		t.setType(2, t.getType(1));
		t.setType(1, temp);
		temp = t.getDirection(0);
		t.setDirection(0, (t.getDirection(3) + 1) % 4);
		t.setDirection(3, (t.getDirection(2) + 1) % 4);
		t.setDirection(2, (t.getDirection(1) + 1) % 4);
		t.setDirection(1, (temp + 1) % 4);
		return t;
	}
}