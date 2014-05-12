import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;

public class Engine {

	private final String SETTINGS_SEPARATOR = "=";

	// maybe have second value a custom object to hold any type of data
	// will have to see how settings are
	private HashMap<String, String> settings;

	private JFrame frame;

	public Engine() {
		initializeVariables();
		initializeSettings();
		initializeFrame();
	}

	private void initializeFrame() {
		frame = new JFrame();
		frame.setTitle(settings.get("title"));
		frame.setPreferredSize(new Dimension(Integer.parseInt(settings
				.get("width")), Integer.parseInt(settings.get("height"))));
		frame.setMinimumSize(new Dimension(Integer.parseInt(settings
				.get("width")), Integer.parseInt(settings.get("height"))));
		frame.setMaximumSize(new Dimension(Integer.parseInt(settings
				.get("width")), Integer.parseInt(settings.get("height"))));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void initializeVariables() {
		settings = new HashMap<String, String>();
	}

	// could maybe write a general file read function to use that for this
	// instead
	private void initializeSettings() {
		// load settings.ini

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
