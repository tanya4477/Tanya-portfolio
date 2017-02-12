/**
 * 
 */
package com.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

/**
 * @author Tanya
 *
 */
public class PowerCalculator {

	private final static String URL_LINK = "http://www.mercado.ren.pt/EN/Electr/MarketInfo/Interconnections/CapForecast/Pages/Daily.aspx";
	private static Map<String, PowerConsumptionVO> dailyConsumptionMap;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		URL daily;
		String inputLine;
		StringBuilder htmlBuilder = new StringBuilder();
		HtmlUtil htmlUtil = new HtmlUtil();
		try {
			daily = new URL(URL_LINK);
			BufferedReader in = new BufferedReader(new InputStreamReader(daily.openStream()));
			while ((inputLine = in.readLine()) != null) {
				htmlBuilder.append(inputLine);
			}

			dailyConsumptionMap = htmlUtil.extractValuesFromHtml(htmlBuilder.toString());

			dailyConsumptionMap.entrySet().forEach(entry -> {
				System.out.println(
						"Actual: " + entry.getKey() + ": " + ((PowerConsumptionVO) entry.getValue()).getActualUsage());
				System.out.println("Forecast: " + entry.getKey() + ": "
						+ ((PowerConsumptionVO) entry.getValue()).getForecastUsage());
			});

			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
