package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

import game.Game;
import game.Tile;
import global.Global;

public class GamePanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Game game;
	private int unit;
	private boolean pause;
	private boolean lost;
	private boolean bombColor;

	public GamePanel(Game game, int width, int height, int unit) {
		this.game = game;
		this.unit = unit;
		this.setPreferredSize(new Dimension(width * unit, height * unit));
		this.pause = false;
		this.lost = false;
		this.bombColor = false;
	}

	public void toggleBomb() {
		this.bombColor = !this.bombColor;
	}

	public void update() {
		this.repaint();
	}

	public void showPause(boolean pause) {
		this.pause = pause;
		this.update();
	}

	public void showLost(boolean lost) {
		this.lost = lost;
		this.update();
	}

	private void paintTile(Graphics g, int x, int y, int type, int direction) {
		switch (type) {
		case 0:
			g.fillRect(x * this.unit + 1, y * this.unit + 1, this.unit - 2, this.unit - 2);
			break;
		case 1:
			switch (direction) {
			case 0:
				for (int x1 = 1; x1 < this.unit - 1; x1++)
					for (int y1 = 1; y1 < this.unit - 1; y1++)
						if ((this.unit - x1) * (this.unit - x1) + y1 * y1 <= this.unit * this.unit)
							g.drawLine(x * this.unit + x1, y * this.unit + y1, x * this.unit + x1, y * this.unit + y1);
				break;
			case 1:
				for (int x1 = 1; x1 < this.unit - 1; x1++)
					for (int y1 = 1; y1 < this.unit - 1; y1++)
						if ((this.unit - x1) * (this.unit - x1) + (this.unit - y1) * (this.unit - y1) <= this.unit
						        * this.unit)
							g.drawLine(x * this.unit + x1, y * this.unit + y1, x * this.unit + x1, y * this.unit + y1);
				break;
			case 2:
				for (int x1 = 1; x1 < this.unit - 1; x1++)
					for (int y1 = 1; y1 < this.unit - 1; y1++)
						if (x1 * x1 + (this.unit - y1) * (this.unit - y1) <= this.unit * this.unit)
							g.drawLine(x * this.unit + x1, y * this.unit + y1, x * this.unit + x1, y * this.unit + y1);
				break;
			case 3:
				for (int x1 = 1; x1 < this.unit - 1; x1++)
					for (int y1 = 1; y1 < this.unit - 1; y1++)
						if (x1 * x1 + y1 * y1 <= this.unit * this.unit)
							g.drawLine(x * this.unit + x1, y * this.unit + y1, x * this.unit + x1, y * this.unit + y1);
				break;
			}
			break;
		case 2:
			switch (direction) {
			case 0:
				g.fillPolygon(new int[] { x * this.unit + 1, (x + 1) * this.unit - 1, (x + 1) * this.unit - 1 },
				        new int[] { y * this.unit + 1, y * this.unit + 1, (y + 1) * this.unit - 1 }, 3);
				break;
			case 1:
				g.fillPolygon(new int[] { (x + 1) * this.unit - 1, x * this.unit + 1, (x + 1) * this.unit - 1 },
				        new int[] { y * this.unit + 1, (y + 1) * this.unit - 1, (y + 1) * this.unit - 1 }, 3);
				break;
			case 2:
				g.fillPolygon(new int[] { x * this.unit + 1, x * this.unit + 1, (x + 1) * this.unit - 1 },
				        new int[] { y * this.unit + 1, (y + 1) * this.unit - 1, (y + 1) * this.unit - 1 }, 3);
				break;
			case 3:
				g.fillPolygon(new int[] { x * this.unit + 1, (x + 1) * this.unit - 1, x * this.unit + 1 },
				        new int[] { y * this.unit + 1, y * this.unit + 1, (y + 1) * this.unit - 1 }, 3);
				break;
			}
			break;
		case 3:
			switch (direction) {
			case 0:
				for (int x1 = 1; x1 < this.unit - 1; x1++)
					for (int y1 = 1; y1 < this.unit - 1; y1++)
						if (x1 * x1 + (this.unit - y1) * (this.unit - y1) >= this.unit * this.unit)
							g.drawLine(x * this.unit + x1, y * this.unit + y1, x * this.unit + x1, y * this.unit + y1);
				break;
			case 1:
				for (int x1 = 1; x1 < this.unit - 1; x1++)
					for (int y1 = 1; y1 < this.unit - 1; y1++)
						if (x1 * x1 + y1 * y1 >= this.unit * this.unit)
							g.drawLine(x * this.unit + x1, y * this.unit + y1, x * this.unit + x1, y * this.unit + y1);
				break;
			case 2:
				for (int x1 = 1; x1 < this.unit - 1; x1++)
					for (int y1 = 1; y1 < this.unit - 1; y1++)
						if ((this.unit - x1) * (this.unit - x1) + y1 * y1 >= this.unit * this.unit)
							g.drawLine(x * this.unit + x1, y * this.unit + y1, x * this.unit + x1, y * this.unit + y1);
				break;
			case 3:
				for (int x1 = 1; x1 < this.unit - 1; x1++)
					for (int y1 = 1; y1 < this.unit - 1; y1++)
						if ((this.unit - x1) * (this.unit - x1) + (this.unit - y1) * (this.unit - y1) >= this.unit
						        * this.unit)
							g.drawLine(x * this.unit + x1, y * this.unit + y1, x * this.unit + x1, y * this.unit + y1);
				break;
			}
			break;
		case 4:
			g.setColor(this.bombColor ? Color.red : Color.black);
			for (int x1 = 2; x1 < this.unit - 2; x1++)
				for (int y1 = 2; y1 < this.unit - 2; y1++)
					if ((x1 - this.unit / 2) * (x1 - this.unit / 2)
					        + (y1 - this.unit / 2) * (y1 - this.unit / 2) <= (this.unit - 4) * (this.unit - 4) / 4)
						g.drawLine(x * this.unit + x1, y * this.unit + y1, x * this.unit + x1, y * this.unit + y1);
			break;
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		for (int x = 0, width = this.game.getWidth(); x < width; x++)
			for (int y = 3, height = this.game.getHeight(); y < height; y++) {
				g.setColor(new Color(0x1a1a1a));
				g.fillRect(x * this.unit, (y - 3) * this.unit, this.unit, this.unit);
				int type = this.game.getType(x, y);
				int direction = this.game.getDirection(x, y);
				g.setColor(!this.game.isComplete(x, y) && this.game.getType(x, y) == 0 ? Color.black
				        : new Color(Global.getColor(type)));
				this.paintTile(g, x, y - 3, type, direction);
				if (this.game.isComplete(x, y) && type != 0) {
					g.setColor(new Color(Global.getColor(4 - type)));
					this.paintTile(g, x, y - 3, 4 - type, (direction + 2) % 4);
				}
			}
		Tile tile = this.game.getTile();
		if (tile != null) {
			for (int i = 0, size = tile.getSize(); i < size; i++) {
				int x = tile.getX(i);
				int y = tile.getY(i);
				int type = tile.getType(i);
				int direction = tile.getDirection(i);
				g.setColor(new Color(Global.getColor(type)));
				this.paintTile(g, x, y - 3, type, direction);
			}
			tile = this.game.getPredictedTile();
			for (int i = 0, size = tile.getSize(); i < size; i++) {
				int x = tile.getX(i);
				int y = tile.getY(i);
				int type = tile.getType(i);
				int direction = tile.getDirection(i);
				g.setColor(new Color(Global.getColor(type) | 0x5f000000, true));
				this.paintTile(g, x, y - 3, type, direction);
			}
		}
		if (this.lost) {
			g.setColor(new Color(0x7fff0000, true));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.red);
			Font font = new Font("Minecraft", Font.PLAIN, 20);
			g.setFont(font);
			FontMetrics fm = g.getFontMetrics(font);
			int i = fm.stringWidth("GAME OVER");
			g.drawString("GAME OVER", (this.getWidth() - i) / 2, 200);
		} else if (this.pause) {
			g.setColor(new Color(0x7faf7f00, true));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.red);
			Font font = new Font("Minecraft", Font.PLAIN, 20);
			g.setFont(font);
			FontMetrics fm = g.getFontMetrics(font);
			int i = fm.stringWidth("PAUSED");
			g.drawString("PAUSED", (this.getWidth() - i) / 2, 200);
		}
	}
}