package univpm.OpenWeather.Service;

import java.net.MalformedURLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface WeatherInt {
	
	public JSONObject getAPI(String name) throws MalformedURLException;
	public JSONObject getCity(String cityName);
	public String toString();
	
}
