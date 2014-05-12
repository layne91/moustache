import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;

public class Engine extends JFrame {

	public static final String SETTINGS_SEPARATOR = "=";

	// maybe have second value a custom object to hold any type of data
	// will have to see how settings are
	private HashMap<String, String> settings;
	private Renderer renderer;

	public Engine() {
		initializeSettings();
		initializeFrame();

		renderer.setBackground(Color.BLACK);
		
	}
	
	private void initializeFrame() {
		setTitle(settings.get("title"));
		setPreferredSize(new Dimension(Integer.parseInt(settings.get("width")),
				Integer.parseInt(settings.get("height"))));
		setMinimumSize(new Dimension(Integer.parseInt(settings.get("width")),
				Integer.parseInt(settings.get("height"))));
		setMaximumSize(new Dimension(Integer.parseInt(settings.get("width")),
				Integer.parseInt(settings.get("height"))));

		renderer = new Renderer(settings);
		add(renderer);

		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	// could maybe write a general file read function to use that for this
	// instead
	private void initializeSettings() {
		// load settings.ini
		settings = new HashMap<String, String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(getClass().getResource(
					"settings.ini").getFile()));
			while (reader.ready()) {
				setSetting(reader.readLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void setSetting(String setting) {
		String param = setting
				.substring(0, setting.indexOf(SETTINGS_SEPARATOR));
		String value = setting.substring(
				setting.indexOf(SETTINGS_SEPARATOR) + 1, setting.length());
		settings.put(param, value);
	}
}
