package game;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private int[][] bricks;
	private Tile tile;
	private Tile nextTile;
	private int width;
	private int height;

	public Game(int width, int height) {
		this.width = width;
		this.height = height + 3;
		this.reset();
	}

	public boolean setTiles() {
		if (this.nextTile == null)
			this.nextTile = this.getRandomTile();
		this.tile = this.nextTile;
		if (!this.check(this.tile, false))
			return false;
		this.nextTile = this.getRandomTile();
		return true;
	}

	private Tile getRandomTile() {
		int i = (int) (Math.random() * 9);
		switch (i) {
		case 0:
			return new TypeA((this.width - 1) / 2, 3).turn((int) (Math.random() * 4));
		case 1:
			return new TypeB((this.width - 1) / 2, 3).turn((int) (Math.random() * 4));
		case 2:
			return new TypeC((this.width - 1) / 2, 3).turn((int) (Math.random() * 4));
		case 3:
			return new TypeD((this.width - 1) / 2, 3).turn((int) (Math.random() * 4));
		case 4:
			return new TypeE((this.width - 1) / 2, 3).turn((int) (Math.random() * 4));
		case 5:
			return new TypeF((this.width - 1) / 2, 3).turn((int) (Math.random() * 4));
		case 6:
			return new TypeG((this.width - 1) / 2, 3).turn((int) (Math.random() * 4));
		case 7:
			return new TypeH((this.width - 1) / 2, 3).turn((int) (Math.random() * 4));
		case 8:
			return new TypeBomb((this.width - 1) / 2, 3).turn((int) (Math.random() * 4));
		}
		return null;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	private void setComplete(int x, int y, boolean complete) {
		this.bricks[x][y] &= 0xf;
		this.bricks[x][y] |= complete ? 0x10 : 0;
	}

	private void setType(int x, int y, int type) {
		this.bricks[x][y] &= 0x1c;
		this.bricks[x][y] |= type;
	}

	private void setDirection(int x, int y, int direction) {
		this.bricks[x][y] &= 0x13;
		this.bricks[x][y] |= direction << 2;
	}

	public boolean isComplete(int x, int y) {
		return (this.bricks[x][y] & 0x10) == 0x10;
	}

	public int getType(int x, int y) {
		return (byte) (this.bricks[x][y] & 0x3);
	}

	public int getDirection(int x, int y) {
		return (byte) ((this.bricks[x][y] & 0xc) >>> 2);
	}

	public Tile getTile() {
		return this.tile == null ? null : this.tile.clone();
	}

	public Tile getNextTile() {
		return this.nextTile == null ? null : this.nextTile.clone();
	}

	public Tile getPredictedTile() {
		Tile t = this.getTile();
		Tile next;
		while (this.check(next = t.moveDown(), true))
			t = next;
		return t;
	}

	private boolean check(Tile t, boolean checkInsert) {
		for (int i = 0, size = t.getSize(); i < size; i++) {
			int x = t.getX(i);
			int y = t.getY(i);
			int type = t.getType(i);
			int direction = t.getDirection(i);
			int thistype;
			if (x < 0 || x >= this.width || y >= this.height || this.isComplete(x, y)
			        || (thistype = this.getType(x, y)) != 0
			                && ((type + thistype) % 4 != 0 || Math.abs(direction - this.getDirection(x, y)) != 2
			                        || checkInsert && (direction == 1 || direction == 2)))
				return false;
		}
		return true;
	}

	private boolean line(int y) {
		for (int x = 0; x < this.width; x++)
			if (!this.isComplete(x, y))
				return false;
		return true;
	}

	private int remove(List<Integer> lines) {
		int line = 0;
		for (int y = 0; y < this.height; y++)
			for (int i = 0, size = lines.size(); i < size; i++)
				if (y == lines.get(i)) {
					line++;
					for (int x = 0; x < this.width; x++)
						for (int y2 = y - 1; y2 >= 0; y2--)
							this.bricks[x][y2 + 1] = this.bricks[x][y2];
					for (int x = 0; x < this.width; x++)
						this.bricks[x][0] = 0;
				}
		return line;
	}

	private int checkLines(int[] ys) {
		List<Integer> lines = new ArrayList<>();
		boolean removed = false;
		for (int i = 0; i < ys.length; i++)
			if (!lines.contains(ys[i]) && this.line(ys[i])) {
				lines.add(ys[i]);
				removed = true;
			}
		int total = 0;
		if (removed) {
			total = this.remove(lines);
			total += this.checkLines(ys);
		}
		return total;
	}

	public int place(Tile t) {
		int size = t.getSize();
		int[] ys = new int[size];
		for (int i = 0; i < size; i++) {
			int x = t.getX(i);
			ys[i] = t.getY(i);
			int type = t.getType(i);
			int direction = t.getDirection(i);
			if (type == 4)
				for (int x1 = x - 1; x1 <= x + 1; x1++) {
					if (x1 >= 0 && x1 < this.width)
						for (int y1 = ys[i] - 1; y1 <= ys[i] + 1; y1++)
							if (y1 >= 0 && y1 < this.height) {
								this.setComplete(x1, y1, false);
								this.setType(x1, y1, 0);
								this.setDirection(x1, y1, 0);
							}
				}
			else if (!this.isComplete(x, ys[i]) && this.getType(x, ys[i]) == 0) {
				this.setComplete(x, ys[i], type == 0);
				this.setType(x, ys[i], type);
				this.setDirection(x, ys[i], direction);
			} else
				this.setComplete(x, ys[i], true);
		}
		return this.checkLines(ys);
	}

	public boolean moveLeft() {
		Tile t = this.tile.moveLeft();
		if (!this.check(t, false))
			return false;
		this.tile = t;
		return true;
	}

	public boolean moveRight() {
		Tile t = this.tile.moveRight();
		if (!this.check(t, false))
			return false;
		this.tile = t;
		return true;
	}

	public boolean moveDown() {
		Tile t = this.tile.moveDown();
		if (!this.check(t, true))
			return false;
		this.tile = t;
		return true;
	}

	public boolean turn() {
		Tile t = this.tile.turn();
		if (!this.check(t, false))
			return false;
		this.tile = t;
		return true;
	}

	public void reset() {
		this.bricks = new int[this.width][this.height];
		this.tile = null;
		this.nextTile = null;
	}
}