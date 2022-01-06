package univpm.OpenWeather.Service;

import java.net.MalformedURLException;

<<<<<<< HEAD

import org.json.simple.JSONObject;

import univpm.OpenWeather.Model.City;
import univpm.OpenWeather.Model.Weather;

public interface WeatherInt {
	
	public Weather getDailyWeather(String name) throws MalformedURLException;
	public JSONObject getInfo(String url) throws MalformedURLException;
	public Weather getWeather(String name) throws MalformedURLException;
	public String toString();
	public String UrlBuilder(String name);

=======
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface WeatherInt {
	
	public JSONObject getAPI(String name) throws MalformedURLException;
	public JSONObject getCity(String cityName);
	public String toString();
	
>>>>>>> refs/remotes/Francesco/FrancescoUpdate
}
