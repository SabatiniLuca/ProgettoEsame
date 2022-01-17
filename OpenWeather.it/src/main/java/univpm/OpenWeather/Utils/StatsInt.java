package univpm.OpenWeather.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.JSONObject;

import univpm.OpenWeather.Exception.WrongValueException;

public interface StatsInt {

	public JSONObject getFiveDaysAverage(String path) throws WrongValueException, FileNotFoundException, IOException;

}
