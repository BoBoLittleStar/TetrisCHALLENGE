package controller;

import game.Game;
import game.Tile;
import view.GamePanel;
import view.OptionPanel;

public class Controller {
	private int width = 10;
	private int height = 20;
	private Game game;
	private GamePanel gameView;
	private OptionPanel optionView;
	private boolean started;
	private boolean paused;
	private int score;
	private int lines;
	private int speed;
	private Runner runner;
	private BombTimer bombTimer;
	private int unit = 20;

	public Controller() {
		this.game = new Game(this.width, this.height);
		this.gameView = new GamePanel(this.game, this.width, this.height, this.unit);
		this.optionView = new OptionPanel(this, this.game, this.unit);
		this.started = false;
		this.paused = false;
		this.score = 0;
		this.lines = 0;
		this.speed = 1;
		this.runner = new Runner(this);
		this.bombTimer = new BombTimer(this);
	}

	public GamePanel getGameView() {
		return this.gameView;
	}

	public OptionPanel getOptionView() {
		return this.optionView;
	}

	public int getWidth() {
		return this.game.getWidth();
	}

	public int getHeight() {
		return this.game.getHeight();
	}

	private void setTiles() {
		boolean lost = !this.game.setTiles();
		this.gameView.showLost(lost);
		if (lost)
			this.stop();
	}

	public void toggleBomb() {
		this.gameView.toggleBomb();
		this.optionView.toggleBomb();
		this.update();
	}

	public int getScore() {
		return this.score;
	}

	public int getLines() {
		return this.lines;
	}

	public int getSpeed() {
		return this.speed;
	}

	public void update() {
		if (this.paused) {
			this.runner.pause();
			this.bombTimer.pause();
		} else {
			this.runner.resume();
			this.bombTimer.resume();
		}
		this.gameView.showPause(this.paused);
		this.gameView.update();
		this.optionView.update();
	}

	public void stop() {
		this.started = false;
		this.paused = false;
		this.runner.stop();
		this.bombTimer.stop();
	}

	public void start() {
		this.stop();
		this.game.reset();
		this.score = 0;
		this.lines = 0;
		this.speed = 1;
		this.setTiles();
		this.started = true;
		this.paused = false;
		this.runner.start();
		this.bombTimer.start();
		this.update();
	}

	public void moveLeft() {
		if (this.started && !this.paused) {
			this.game.moveLeft();
			this.update();
		}
	}

	public void moveRight() {
		if (this.started && !this.paused) {
			this.game.moveRight();
			this.update();
		}
	}

	public void moveDown() {
		if (this.started && !this.paused)
			if (this.game.moveDown())
				this.update();
			else
				this.place();
	}

	private void score(Tile tile, int multiplier) {
		int lines = this.game.place(tile);
		if (lines > 0) {
			this.score += lines * (lines + 1) * multiplier;
			this.lines += lines;
			this.speed = (byte) Math.min(20, this.lines / 20 + 1);
		}
		this.setTiles();
		this.update();
	}

	private void place() {
		if (this.started && !this.paused)
			synchronized (this) {
				this.score(this.game.getTile(), 10);
			}
	}

	public void fall() {
		if (this.started && !this.paused)
			synchronized (this) {
				this.score(this.game.getPredictedTile(), 20);
			}
	}

	public void turn() {
		if (this.started && !this.paused) {
			this.game.turn();
			this.update();
		}
	}

	public void pause() {
		if (this.started && !this.paused) {
			this.paused = true;
			this.update();
		}
	}

	public void resume() {
		if (this.started && this.paused) {
			this.paused = false;
			this.update();
		}
	}

	public boolean paused() {
		return this.paused;
	}

	public void quit() {
	}
}