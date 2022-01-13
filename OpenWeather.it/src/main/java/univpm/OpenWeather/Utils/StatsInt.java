package univpm.OpenWeather.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.JSONObject;

import univpm.OpenWeather.Exception.NullObjectException;
import univpm.OpenWeather.Model.Weather;

public interface StatsInt {
	
	public JSONObject getFiveDaysAverage(String path) throws NullObjectException, FileNotFoundException, IOException;

	
}
