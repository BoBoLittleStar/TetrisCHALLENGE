package view;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import controller.Controller;

public class MainFrame extends JFrame implements KeyListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Controller ctrl;

	public MainFrame() {
		super("Tetris");
		this.ctrl = new Controller();
		this.add(this.ctrl.getGameView());
		this.add(this.ctrl.getOptionView(), BorderLayout.WEST);
		this.addKeyListener(this);
		this.pack();
		this.setLocationRelativeTo(null);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_LEFT:
			this.ctrl.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			this.ctrl.moveRight();
			break;
		case KeyEvent.VK_UP:
			this.ctrl.turn();
			break;
		case KeyEvent.VK_DOWN:
			this.ctrl.moveDown();
			break;
		case KeyEvent.VK_SPACE:
			this.ctrl.fall();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}