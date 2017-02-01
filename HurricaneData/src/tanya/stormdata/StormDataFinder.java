package tanya.stormdata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This class displays all storm speed for the year 2009.
 * 
 * @author Tanya
 *
 */
public class StormDataFinder {
	private static final String FILENAME = "src/hurdat2-nepac-1949-2015-050916.txt";
	private static final String YEAR = "2009";

	public static void main(String[] args) {
		Map<String, Integer> stormSpeedMap = new LinkedHashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

			boolean captureData = false;
			String tempStormName = null;
			int maxStormSpeed = 0;
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				String firstWord = currentLine.split("\\s", 0)[0];
				if (firstWord.trim().endsWith(YEAR + ",")) {
					maxStormSpeed = 0;
					// storm name
					tempStormName = currentLine.subSequence(18, 28).toString().trim();
					stormSpeedMap.put(tempStormName, 0);
					captureData = true;
				} else if (firstWord.startsWith(YEAR) && captureData) {
					// wind speed
					int tempStormSpeed = Integer.parseInt(currentLine.subSequence(38, 41).toString().trim());
					if (tempStormSpeed > maxStormSpeed) {
						maxStormSpeed = tempStormSpeed;
						stormSpeedMap.put(tempStormName, maxStormSpeed);
					}
				} else {
					captureData = false;
					tempStormName = null;
					maxStormSpeed = 0;
				}
			}
			System.out.printf("%-20s%-20s\n", "Storm Name", "Maximum Wind Speed");
			System.out.printf("%-20s%-20s\n", "----------", "-------------------");
			for (Entry<String, Integer> entry : stormSpeedMap.entrySet()) {
				System.out.printf("%-20s%-20s\n", entry.getKey(), entry.getValue() + " knots");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
