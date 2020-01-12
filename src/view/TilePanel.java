package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import game.Game;
import game.Tile;
import global.Global;

public class TilePanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Game game;
	private int unit;
	private boolean bombColor;

	TilePanel(Game game, int unit) {
		this.game = game;
		this.unit = unit;
		this.setPreferredSize(new Dimension(5 * unit, 5 * unit));
		this.bombColor = false;
	}

	public void toggleBomb() {
		this.bombColor = !this.bombColor;
	}

	public void update() {
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		Tile tile = this.game.getNextTile();
		if (tile != null) {
			int size = tile.getSize();
			int[] xs = new int[size];
			int[] ys = new int[size];
			for (int i = 0; i < size; i++) {
				xs[i] = tile.getX(i);
				ys[i] = tile.getY(i);
			}
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < size; i++)
				min = min > xs[i] ? xs[i] : min;
			for (int i = 0; i < size; i++)
				xs[i] = xs[i] - min;
			min = Integer.MAX_VALUE;
			for (int i = 0; i < size; i++)
				min = min > ys[i] ? ys[i] : min;
			for (int i = 0; i < size; i++)
				ys[i] = ys[i] - min;
			for (int i = 0; i < size; i++) {
				int type = tile.getType(i);
				int direction = tile.getDirection(i);
				g.setColor(new Color(0x1a1a1a));
				g.fillRect((xs[i] + 1) * this.unit, (ys[i] + 1) * this.unit, this.unit, this.unit);
				g.setColor(new Color(Global.getColor(type)));
				switch (type) {
				case 0:
					g.fillRect((xs[i] + 1) * this.unit + 1, (ys[i] + 1) * this.unit + 1, this.unit - 2, this.unit - 2);
					break;
				case 1:
					switch (direction) {
					case 0:
						for (int x = 1; x < this.unit - 1; x++)
							for (int y = 1; y < this.unit - 1; y++)
								if ((this.unit - x) * (this.unit - x) + y * y <= this.unit * this.unit)
									g.drawLine(xs[i] * this.unit + x + this.unit, ys[i] * this.unit + y + this.unit,
									        xs[i] * this.unit + x + this.unit, ys[i] * this.unit + y + this.unit);
						break;
					case 1:
						for (int x = 1; x < this.unit - 1; x++)
							for (int y = 1; y < this.unit - 1; y++)
								if ((this.unit - x) * (this.unit - x) + (this.unit - y) * (this.unit - y) <= this.unit
								        * this.unit)
									g.drawLine(xs[i] * this.unit + x + this.unit, ys[i] * this.unit + y + this.unit,
									        xs[i] * this.unit + x + this.unit, ys[i] * this.unit + y + this.unit);
						break;
					case 2:
						for (int x = 1; x < this.unit - 1; x++)
							for (int y = 1; y < this.unit - 1; y++)
								if (x * x + (this.unit - y) * (this.unit - y) <= this.unit * this.unit)
									g.drawLine(xs[i] * this.unit + x + this.unit, ys[i] * this.unit + y + this.unit,
									        xs[i] * this.unit + x + this.unit, ys[i] * this.unit + y + this.unit);
						break;
					case 3:
						for (int x = 1; x < this.unit - 1; x++)
							for (int y = 1; y < this.unit - 1; y++)
								if (x * x + y * y <= this.unit * this.unit)
									g.drawLine(xs[i] * this.unit + x + this.unit, ys[i] * this.unit + y + this.unit,
									        xs[i] * this.unit + x + this.unit, ys[i] * this.unit + y + this.unit);
						break;
					}
					break;
				case 2:
					switch (direction) {
					case 0:
						g.fillPolygon(
						        new int[] { (xs[i] + 1) * this.unit + 1, (xs[i] + 2) * this.unit - 1,
						                (xs[i] + 2) * this.unit - 1 },
						        new int[] { (ys[i] + 1) * this.unit + 1, (ys[i] + 1) * this.unit + 1,
						                (ys[i] + 2) * this.unit - 1 },
						        3);
						break;
					case 1:
						g.fillPolygon(
						        new int[] { (xs[i] + 2) * this.unit - 1, (xs[i] + 1) * this.unit + 1,
						                (xs[i] + 2) * this.unit - 1 },
						        new int[] { (ys[i] + 1) * this.unit + 1, (ys[i] + 2) * this.unit - 1,
						                (ys[i] + 2) * this.unit - 1 },
						        3);
						break;
					case 2:
						g.fillPolygon(
						        new int[] { (xs[i] + 1) * this.unit + 1, (xs[i] + 1) * this.unit + 1,
						                (xs[i] + 2) * this.unit - 1 },
						        new int[] { (ys[i] + 1) * this.unit + 1, (ys[i] + 2) * this.unit - 1,
						                (ys[i] + 2) * this.unit - 1 },
						        3);
						break;
					case 3:
						g.fillPolygon(
						        new int[] { (xs[i] + 1) * this.unit + 1, (xs[i] + 2) * this.unit - 1,
						                (xs[i] + 1) * this.unit + 1 },
						        new int[] { (ys[i] + 1) * this.unit + 1, (ys[i] + 1) * this.unit + 1,
						                (ys[i] + 2) * this.unit - 1 },
						        3);
						break;
					}
					break;
				case 3:
					switch (direction) {
					case 0:
						for (int x = 1; x < this.unit - 1; x++)
							for (int y = 1; y < this.unit - 1; y++)
								if (x * x + (this.unit - y) * (this.unit - y) >= this.unit * this.unit)
									g.drawLine(xs[i] * this.unit + x + this.unit, ys[i] * this.unit + y + this.unit,
									        xs[i] * this.unit + x + this.unit, ys[i] * this.unit + y + this.unit);
						break;
					case 1:
						for (int x = 1; x < this.unit - 1; x++)
							for (int y = 1; y < this.unit - 1; y++)
								if (x * x + y * y >= this.unit * this.unit)
									g.drawLine(xs[i] * this.unit + x + this.unit, ys[i] * this.unit + y + this.unit,
									        xs[i] * this.unit + x + this.unit, ys[i] * this.unit + y + this.unit);
						break;
					case 2:
						for (int x = 1; x < this.unit - 1; x++)
							for (int y = 1; y < this.unit - 1; y++)
								if ((this.unit - x) * (this.unit - x) + y * y >= this.unit * this.unit)
									g.drawLine(xs[i] * this.unit + x + this.unit, ys[i] * this.unit + y + this.unit,
									        xs[i] * this.unit + x + this.unit, ys[i] * this.unit + y + this.unit);
						break;
					case 3:
						for (int x = 1; x < this.unit - 1; x++)
							for (int y = 1; y < this.unit - 1; y++)
								if ((this.unit - x) * (this.unit - x) + (this.unit - y) * (this.unit - y) >= this.unit
								        * this.unit)
									g.drawLine(xs[i] * this.unit + x + this.unit, ys[i] * this.unit + y + this.unit,
									        xs[i] * this.unit + x + this.unit, ys[i] * this.unit + y + this.unit);
						break;
					}
					break;
				case 4:
					g.setColor(this.bombColor ? Color.red : Color.black);
					for (int x = 2; x < this.unit - 2; x++)
						for (int y = 2; y < this.unit - 2; y++)
							if ((x - this.unit / 2) * (x - this.unit / 2) + (y - this.unit / 2)
							        * (y - this.unit / 2) <= (this.unit - 4) * (this.unit - 4) / 4)
								g.drawLine(xs[i] * this.unit + x + this.unit, ys[i] * this.unit + y + this.unit,
								        xs[i] * this.unit + x + this.unit, ys[i] * this.unit + y + this.unit);
				}
			}
		}
	}
}