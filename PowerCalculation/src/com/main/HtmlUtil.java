/**
 * 
 */
package com.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * This class helps in extracting the values from the html file
 * 
 * @author Tanya
 *
 */
public class HtmlUtil {
	private List<List<PowerConsumptionVO>> hourlyConsumptionDataList;
	private final Integer NUMBER_OF_DAYS = 15;
	private final String TABLE_DATA_TAG = "td";
	private final String TABLE_HEADER_TAG = "th";
	private final String FORECAST_DATA_STYLE_1 = "txtrPREV";
	private final String FORECAST_DATA_STYLE_2 = "txtPREV";
	private final String ACTUAL_DATA_STYLE_1 = "txtrVERIF";
	private final String ACTUAL_DATA_STYLE_2 = "txtVERIF";
	private final String HEADER_STYLE_1 = "rbcellBDRCOLOR";
	private final String HEADER_STYLE_2 = "bcellBDRCOLOR";
	private final Integer YEAR = 2017;

	/**
	 * 
	 */
	HtmlUtil() {
		hourlyConsumptionDataList = new ArrayList<>();
	}

	public Map<String, PowerConsumptionVO> extractValuesFromHtml(String html) {
		Map<String, PowerConsumptionVO> dailyPowerConsumptionMap = new LinkedHashMap<>();
		Document document = Jsoup.parse(html);

		// Extract the hours from the table data
		List<PowerConsumptionVO> hourlyList = new ArrayList<>();
		Elements tableData = document.select(TABLE_DATA_TAG);
		int dayCount = 1;
		for (Element data : tableData) {
			if (data.className().equals(FORECAST_DATA_STYLE_1) || data.className().equals(FORECAST_DATA_STYLE_2)
					|| data.className().equals(ACTUAL_DATA_STYLE_1) || data.className().equals(ACTUAL_DATA_STYLE_2)) {
				int val = Integer.parseInt(data.ownText());
				if (data.className().equals(ACTUAL_DATA_STYLE_1) || data.className().equals(ACTUAL_DATA_STYLE_2)) {
					hourlyList.add(new PowerConsumptionVO(0, val));
				} else {
					hourlyList.add(new PowerConsumptionVO(val, 0));
				}

				if (dayCount++ == NUMBER_OF_DAYS) {
					hourlyConsumptionDataList.add(hourlyList);
					hourlyList = new ArrayList<>();
					dayCount = 1;
				}
			}
		}

		// Extract the days (such as 20 Jan, 21 Jan... so on) from the headers
		int index = 0;
		Elements tableHeader = document.select(TABLE_HEADER_TAG);
		for (Element header : tableHeader) {
			if (header.className().equals(HEADER_STYLE_1) || header.className().equals(HEADER_STYLE_2)) {
				String headerText = header.ownText().replaceAll("\u00a0", "-").trim(); // To replace &nbsp; with '-'
				// Eliminate the header with "HOUR"
				if (!headerText.contains("HOUR") && index < NUMBER_OF_DAYS) {
					Integer forecastUsageSum = 0;
					Integer actualUsageSum = 0;
					for (List<PowerConsumptionVO> list : hourlyConsumptionDataList) {
						forecastUsageSum += list.get(index).getForecastUsage();
						actualUsageSum += list.get(index).getActualUsage();
					}

					dailyPowerConsumptionMap.put(changeDateFormat(headerText),
							new PowerConsumptionVO(forecastUsageSum, actualUsageSum));
					index++;
				}
			}
		}

		return dailyPowerConsumptionMap;
	}

	/**
	 * Utility method to convert dates from dd-mmm-yyyy to yyyy-MM-dd format
	 * 
	 * @param inputDate
	 * @return Date in String format
	 */
	private String changeDateFormat(String inputDate) {
		String strDate = inputDate.concat("-" + YEAR);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Date formattedDate = null;
		try {
			formattedDate = dateFormat.parse(strDate);
			dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateFormat.format(formattedDate).toString();
	}
}
