package univpm.OpenWeather.Service;

import java.net.MalformedURLException;


import org.json.simple.JSONObject;

import univpm.OpenWeather.Model.City;
import univpm.OpenWeather.Model.Weather;

public interface WeatherInt {
	
	
	public JSONObject getInfo(String url) throws MalformedURLException;
	public Weather getWeather(String name, Weather meteo) throws MalformedURLException;
	public String toString();
	public String UrlBuilder(boolean current, String cityName);
	public JSONObject printInfo(Weather city);
	public City getCity(String cityName, Weather meteo) throws MalformedURLException;

}
