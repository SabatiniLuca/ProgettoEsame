package univpm.OpenWeather.Utils;

import org.json.simple.JSONObject;

import univpm.OpenWeather.Exception.NullObjectException;

public interface StatsInt {
	
	public JSONObject getFiveDaysAverage(JSONObject jobj ) throws NullObjectException;
	

}
