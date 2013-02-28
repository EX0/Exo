package de.dariusmewes.exo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();

	public InputHandler(Exo game) {
		game.addKeyListener(this);
	}

	public void keyPressed(KeyEvent event) {
		switch (event.getKeyCode()) {
		case KeyEvent.VK_W:
			up.set(true);
			break;
		case KeyEvent.VK_A:
			left.set(true);
			break;
		case KeyEvent.VK_S:
			down.set(true);
			break;
		case KeyEvent.VK_D:
			right.set(true);
			break;
		default:
			break;
		}
	}

	public void keyReleased(KeyEvent event) {
		switch (event.getKeyCode()) {
		case KeyEvent.VK_W:
			up.set(false);
			break;
		case KeyEvent.VK_A:
			left.set(false);
			break;
		case KeyEvent.VK_S:
			down.set(false);
			break;
		case KeyEvent.VK_D:
			right.set(false);
			break;
		default:
			break;
		}
	}

	public void keyTyped(KeyEvent event) {

	}

	public class Key {
		private boolean pressed = false;

		public void set(boolean pressed) {
			this.pressed = pressed;
		}

		public boolean isPressed() {
			return pressed;
		}
	}
}