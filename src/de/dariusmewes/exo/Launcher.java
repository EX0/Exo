package de.dariusmewes.exo;

import java.applet.Applet;
import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Launcher extends Applet {
	private static final long serialVersionUID = 1L;

	private static Exo game = new Exo();

	public void init() {
		setLayout(new BorderLayout());
		add(game, BorderLayout.CENTER);
		setMinimumSize(Exo.SIZE);
		setMaximumSize(Exo.SIZE);
		setPreferredSize(Exo.SIZE);
		game.isApplet = true;
	}

	public void start() {
		game.start();
	}

	public void stop() {
		game.stop();
	}

	public static void main(String[] args) {
		game.setMinimumSize(Exo.SIZE);
		game.setMaximumSize(Exo.SIZE);
		game.setPreferredSize(Exo.SIZE);

		game.frame = new JFrame(Exo.TITLE);
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLayout(new BorderLayout());
		game.frame.add(game, BorderLayout.CENTER);
		game.frame.pack();
		game.frame.setResizable(false);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.isApplet = false;
		game.start();
	}

}
