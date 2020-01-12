package controller;

public class BombTimer implements Runnable {
	private Thread t;
	private Controller ctrl;
	private boolean paused;

	public BombTimer(Controller ctrl) {
		this.ctrl = ctrl;
		this.paused = false;
	}

	public void start() {
		this.stop();
		this.t = new Thread(this);
		this.t.start();
	}

	public void stop() {
		if (this.t != null)
			this.t.interrupt();
		this.paused = false;
	}

	public void pause() {
		this.paused = true;
	}

	public void resume() {
		this.paused = false;
		synchronized (this) {
			this.notify();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				for (int i = 0, speed = 200; i < speed; i++) {
					if (this.paused)
						synchronized (this) {
							this.wait();
						}
					Thread.sleep(1);
				}
				this.ctrl.toggleBomb();
			}
		} catch (@SuppressWarnings("unused") InterruptedException E) {
		}
	}
}