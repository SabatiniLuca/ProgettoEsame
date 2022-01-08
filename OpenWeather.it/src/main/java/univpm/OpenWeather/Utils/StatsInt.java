package univpm.OpenWeather.Utils;

import java.text.ParseException;

import org.json.simple.JSONObject;

import univpm.OpenWeather.Model.Weather;

public interface StatsInt {
	
	public Weather getDailyWeather(JSONObject jobj, Weather meteo) throws ParseException;
	

}
