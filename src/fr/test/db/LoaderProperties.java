package fr.test.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//pour lire un fichier properties d'app - un fichier de configuration
public class LoaderProperties {
	final private static String FILE_PROPERTIES = "config.properties";
	final static Properties properties = new Properties();

	public Properties loader() throws FileNotFoundException {

		InputStream input = null;

		try {
			ClassLoader cload = this.getClass().getClassLoader();
			input = cload.getResourceAsStream(FILE_PROPERTIES);
			properties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return properties;
	}

	public String getDriver() {
		return properties.getProperty("driveJDBC");
	}

	public String getDatabaseURL() {
		return properties.getProperty("databaseURL");
	}

	public String getUserDB() {
		return properties.getProperty("user");

	}

	public String getPassDB() {
		return properties.getProperty("password");
	}

}
