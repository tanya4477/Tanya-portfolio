/**
 * 
 */
package com.main;

/**
 * This is the POJO class for Power Consumption
 * 
 * @author Tanya
 *
 */
public class PowerConsumptionVO {
	private int forecastUsage;
	private int actualUsage;

	/**
	 * @param forecastUsage
	 * @param actualUsage
	 */
	public PowerConsumptionVO(int forecastUsage, int actualUsage) {
		this.forecastUsage = forecastUsage;
		this.actualUsage = actualUsage;
	}

	public int getForecastUsage() {
		return forecastUsage;
	}

	public void setForecastUsage(int forecastUsage) {
		this.forecastUsage = forecastUsage;
	}

	public int getActualUsage() {
		return actualUsage;
	}

	public void setActualUsage(int actualUsage) {
		this.actualUsage = actualUsage;
	}

}
