package com.swathi.mysqljdbc.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropFileReader {

	public static String readData(String key) {
		String value = null;
		Properties prop = new Properties();

		try {
			prop.load(new FileInputStream("src/main/resources/connection.properties"));
			value = prop.getProperty(key);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return value;
	}
}
