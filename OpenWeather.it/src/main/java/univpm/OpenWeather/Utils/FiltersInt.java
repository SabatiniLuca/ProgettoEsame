package univpm.OpenWeather.Utils;

import java.text.ParseException;

import org.json.simple.JSONObject;

public interface FiltersInt {
	public JSONObject selectDay(String day) throws ParseException;
	public JSONObject fiveDaysFromNow() throws ParseException;
	public JSONObject oneDayFromNow();
	public JSONObject onlyToday();
}
