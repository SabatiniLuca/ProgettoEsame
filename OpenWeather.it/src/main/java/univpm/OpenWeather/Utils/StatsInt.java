package univpm.OpenWeather.Utils;

import org.json.simple.JSONObject;

import univpm.OpenWeather.Model.Weather;

public interface StatsInt {
	
	public Weather getDailyWeather(JSONObject jobj, Weather meteo);
	

}
