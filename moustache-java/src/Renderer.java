import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.JPanel;

public class Renderer extends JPanel {

	private HashMap<String, String> settings;

	public Renderer(HashMap<String, String> settings) {
		this.settings = settings;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		debugDraw(g);
	}

	private void debugDraw(Graphics g) {
		g.setColor(Color.WHITE);
		int y = 20;
		for (String key : settings.keySet()) {
			g.drawString(key + Engine.SETTINGS_SEPARATOR + settings.get(key),
					10, y);
			y += 10;
		}
	}

}
