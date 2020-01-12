package controller;

public class Runner implements Runnable {
	private Thread t;
	private Controller ctrl;
	private boolean paused;
	private int speed;

	public Runner(Controller ctrl) {
		this.ctrl = ctrl;
		this.paused = false;
		this.speed = 1;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
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
		this.speed = 1;
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
				for (int i = 0, speed = 1000 / this.speed; i < speed; i++) {
					if (this.paused)
						synchronized (this) {
							this.wait();
						}
					Thread.sleep(1);
				}
				this.ctrl.moveDown();
			}
		} catch (@SuppressWarnings("unused") InterruptedException E) {
		}
	}
}