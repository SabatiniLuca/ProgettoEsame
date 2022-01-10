package univpm.OpenWeather.Service;

import java.net.MalformedURLException;


import org.json.simple.JSONObject;

import univpm.OpenWeather.Model.Weather;

public interface WeatherInt {
	
	
	public JSONObject getInfo(String url) throws MalformedURLException;
	public String UrlBuilder(boolean current, String cityName);
	public Weather getWeather(String name, Weather meteo) throws MalformedURLException;
	public String toString();
	public JSONObject printInfo(Weather city, boolean all);
	public String saveHourlyWeather(String name, Weather weather);

}
