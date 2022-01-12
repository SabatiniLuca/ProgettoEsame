package univpm.OpenWeather.Utils;

import org.json.simple.JSONObject;

import univpm.OpenWeather.Exception.NullObjectException;
import univpm.OpenWeather.Model.Weather;

public interface StatsInt {
	
	public JSONObject getFiveDaysAverage(JSONObject jobj , Weather meteo) throws NullObjectException;
	

}
