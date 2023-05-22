package dataproviders;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import enums.BroswerType;

public class PropertiesFileReader {
	private HashMap<String, Properties> propertiesMap = new HashMap<>();
	private static PropertiesFileReader fileReader = null;

	/**
	 * @param sPropFileName : property file name
	 * @return obj.get(sPropFileName): Property file
	 */
	public Properties getPropertyFile(String sPropFileName) {
		Properties prop;

		try {
			if (propertiesMap.get(sPropFileName) == null) {
				prop = new Properties();
				prop.load(this.getClass().getClassLoader().getResourceAsStream(sPropFileName));
				propertiesMap.put(sPropFileName, prop);
			}
		} catch (IOException e) {
			System.out.println("Exception while loading the properties file");
		}
		return propertiesMap.get(sPropFileName);
	}

	/**
	 * @return PropertiesFileReader Instance.
	 */
	public static PropertiesFileReader getInstance() {
		if (fileReader != null) {
			return fileReader;
		}
		synchronized (PropertiesFileReader.class) {
			if (fileReader == null) {
				fileReader = new PropertiesFileReader();
			}
			return fileReader;
		}
	}

	public Properties getConfigProperties() {
		return getPropertyFile("Config.properties");
	}

	public String getRelativeDriverPath() {
		String browser = getPropertyFile("Config.properties").getProperty("browser").trim();
		String driverPath = null;
		if (browser.equalsIgnoreCase("Chrome")) {
			driverPath = getPropertyFile("Config.properties").getProperty("chromeDriverPath");
		} else if (browser.equalsIgnoreCase("Firefox")) {
			driverPath = getPropertyFile("Config.properties").getProperty("firefoxDriverPath");
		}

		if (driverPath != null)
			return driverPath;
		else
			throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
	}

	public long getImplicitlyWait() {
		String implicitlyWait = getPropertyFile("Config.properties").getProperty("implicitlyWait");
		if (implicitlyWait != null)
			return Long.parseLong(implicitlyWait);
		else
			throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");
	}

	public String getApplicationUrl() {
		String url = getPropertyFile("Config.properties").getProperty("url");
		if (url != null)
			return url;
		else
			throw new RuntimeException("url not specified in the Configuration.properties file.");
	}

	public BroswerType getBrowser() {
		String browserName = getPropertyFile("Config.properties").getProperty("browser");
		if (browserName == null || browserName.equalsIgnoreCase("chrome"))
			return BroswerType.CHROME;
		else if (browserName.equalsIgnoreCase("firefox"))
			return BroswerType.FIREFOX;
		else
			throw new RuntimeException(
					"Browser Name Key value in Config.properties is not matched : " + browserName);
	}
}
