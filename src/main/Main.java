package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.MainFrame;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
	        IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(javax.swing.plaf.metal.MetalLookAndFeel.class.getName());
		MainFrame mf = new MainFrame();
		mf.setVisible(true);
	}
}