package univpm.OpenWeather.Service;

import java.net.MalformedURLException;
import java.text.ParseException;

import org.json.simple.JSONObject;

import univpm.OpenWeather.Exception.NullObjectException;
import univpm.OpenWeather.Model.Weather;

public interface WeatherInt {
	
	
	public JSONObject getInfo(String url) throws MalformedURLException;
	public String UrlBuilder(boolean current, String cityName);
	public Weather getWeather(String name) throws MalformedURLException;//, Weather meteo
	public JSONObject getForecast(String cityName) throws MalformedURLException, ParseException;
	public String toString();
	public JSONObject printInfo(Weather city, boolean all);
	public JSONObject saveHourlyWeatherAndStats(String name,boolean stats) throws NullObjectException;//, Weather weather
	public String saveFile(String name);

}
