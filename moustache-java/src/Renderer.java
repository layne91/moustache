import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.JPanel;

public class Renderer extends JPanel {

	private final float MAX_FPS = 30.0f;
	
	private HashMap<String, String> settings;
	private float frames = 0.0f;
	private float fps = 0.0f;

	private long currentTime = timeMilliseconds();
	private float deltaTime = 1.0f;

	public Renderer(HashMap<String, String> settings) {
		this.settings = settings;
	}

	@Override
	protected void paintComponent(Graphics g) {
		long time = timeMilliseconds();
		super.paintComponent(g);
		
		for(int i = 0; i < 100; ++i){
			int x = (int)(Math.random() * 1000);
			int y = (int)(Math.random() * 1000);
			g.drawRect(x, y, 10, 10);
		}
		
		debugDraw(g);

		
		
		limitFPS(time);
		frames++;
	}

	private void limitFPS(long time){
		long frameDuration = timeMilliseconds() - time;
		while(frameDuration < ((1.0f/MAX_FPS) * 1000.0f)){
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			frameDuration = timeMilliseconds() - time;
		}
		deltaTime = frameDuration;
	}
	
	public void update() {

		calculateFPS();
	}

	private void calculateFPS(){
		repaint();
		
		if(timeMilliseconds() - currentTime > 1000){
			frames = 0;
			fps = (1.0f/deltaTime)*1000.0f;
			currentTime += 1000;
		}
		

	}
	
	private void debugDraw(Graphics g) {
		g.setColor(Color.WHITE);
		int y = 50;
		g.drawString("fps=" + fps, 10, 20);
		g.drawString("frames=" + frames, 10, 30);
		for (String key : settings.keySet()) {
			g.drawString(key + Engine.SETTINGS_SEPARATOR + settings.get(key),
					10, y);
			y += 10;
		}
	}

	private long timeMilliseconds(){
		return System.nanoTime() / 1000000;
	}

}
