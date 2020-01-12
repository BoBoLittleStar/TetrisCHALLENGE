package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.Controller;
import game.Game;

public class OptionPanel extends JPanel implements MouseListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Controller ctrl;
	private JLabel start;
	private JLabel pause;
	private JLabel resume;
	private JLabel quit;
	private TilePanel next;
	private JLabel score;
	private JLabel lines;
	private JLabel speed;

	public OptionPanel(Controller ctrl, Game game, int unit) {
		this.ctrl = ctrl;
		this.start = new JLabel("Start", SwingConstants.CENTER);
		this.pause = new JLabel("Pause", SwingConstants.CENTER);
		this.resume = new JLabel("Resume", SwingConstants.CENTER);
		this.quit = new JLabel("Quit", SwingConstants.CENTER);
		this.next = new TilePanel(game, unit);
		this.score = new JLabel("0", SwingConstants.RIGHT);
		this.lines = new JLabel("0", SwingConstants.RIGHT);
		this.speed = new JLabel("1", SwingConstants.RIGHT);
		int width = Math.max(5 * unit, 120);
		this.start.setBounds(10, 10, width, 30);
		this.pause.setBounds(10, 50, width, 30);
		this.resume.setBounds(10, 90, width, 30);
		this.quit.setBounds(10, 130, width, 30);
		int x = Math.max(70 - 5 * unit / 2, 10);
		this.next.setBounds(x, 170, 5 * unit, 5 * unit);
		this.score.setBounds(10, 180 + 5 * unit, width, 30);
		this.lines.setBounds(10, 220 + 5 * unit, width, 30);
		this.speed.setBounds(10, 260 + 5 * unit, width, 30);
		this.start.setOpaque(true);
		this.pause.setOpaque(true);
		this.resume.setOpaque(true);
		this.quit.setOpaque(true);
		this.score.setOpaque(true);
		this.lines.setOpaque(true);
		this.speed.setOpaque(true);
		Color color = new Color(0x7fafff);
		this.start.setBackground(color);
		this.pause.setBackground(color);
		this.resume.setBackground(color);
		this.quit.setBackground(color);
		this.score.setBackground(color);
		this.lines.setBackground(color);
		this.speed.setBackground(color);
		Font font = new Font("Minecraft", Font.PLAIN, 20);
		this.start.setFont(font);
		this.pause.setFont(font);
		this.resume.setFont(font);
		this.quit.setFont(font);
		this.score.setFont(font);
		this.lines.setFont(font);
		this.speed.setFont(font);
		this.start.addMouseListener(this);
		this.pause.addMouseListener(this);
		this.resume.addMouseListener(this);
		this.quit.addMouseListener(this);
		this.setPreferredSize(new Dimension(width + 20, 5 * unit + 260));
		this.setLayout(null);
		this.add(this.start);
		this.add(this.pause);
		this.add(this.resume);
		this.add(this.quit);
		this.add(this.next);
		this.add(this.score);
		this.add(this.lines);
		this.add(this.speed);
	}

	public void toggleBomb() {
		this.next.toggleBomb();
	}

	public void update() {
		this.next.update();
		this.score.setText(String.valueOf(this.ctrl.getScore()));
		this.lines.setText(String.valueOf(this.ctrl.getLines()));
		this.speed.setText(String.valueOf(this.ctrl.getSpeed()));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		if (source == this.start)
			this.ctrl.start();
		else if (source == this.pause)
			this.ctrl.pause();
		else if (source == this.resume)
			this.ctrl.resume();
		else if (source == this.quit) {
			this.ctrl.quit();
			System.exit(0);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object source = e.getSource();
		if (source == this.start)
			this.start.setForeground(Color.white);
		else if (source == this.pause)
			this.pause.setForeground(Color.white);
		else if (source == this.resume)
			this.resume.setForeground(Color.white);
		else if (source == this.quit)
			this.quit.setForeground(Color.white);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object source = e.getSource();
		if (source == this.start)
			this.start.setForeground(Color.black);
		else if (source == this.pause)
			this.pause.setForeground(Color.black);
		else if (source == this.resume)
			this.resume.setForeground(Color.black);
		else if (source == this.quit)
			this.quit.setForeground(Color.black);
	}
}