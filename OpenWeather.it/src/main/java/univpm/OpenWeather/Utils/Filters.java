package univpm.OpenWeather.Utils;

import org.json.simple.JSONObject;

public interface Filters {
		
	public JSONObject ToJSON();
	public JSONObject DailyAverage();
	public JSONObject FiveDaysAverage();
	

}
