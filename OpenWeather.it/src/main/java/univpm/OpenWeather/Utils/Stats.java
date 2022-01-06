package univpm.OpenWeather.Utils;

import org.json.simple.JSONObject;


import univpm.OpenWeather.Model.Weather;
import univpm.OpenWeather.Service.WeatherImpl;

public class Stats implements StatsInt{
	
	WeatherImpl service = new WeatherImpl();
	
	public Weather getDailyWeather(JSONObject jobj, Weather meteo) {
		
		System.out.println(jobj);
		
		JSONObject tempObject=(JSONObject) jobj.get("main");//prende i dati delle temperature
		double temp =(double) tempObject.get("temp"); 
		double temp_max= (double) tempObject.get("temp_max");
		double temp_min= (double) tempObject.get("temp_min");
		meteo.setTemp(temp);
		meteo.setTemp_min(temp_min);
		meteo.setTemp_max(temp_max);
				
			
		long pressure=(long) tempObject.get("pressure");
		meteo.setPressure(pressure);
		
		
		
				
		/*
		try {
			JSONArray meteoArr = (JSONArray) jobj.get("list");
			JSONObject meteoObj = (JSONObject) meteoArr.get(0);
			double temp = (double) meteoObj.get("temp");
			String feels_like = (String) meteoObj.get("feels_like");
			double temp_min = (double) meteoObj.get("temp_min");
			double temp_max = (double) meteoObj.get("temp_max");
			double pressure = (double) meteoObj.get("pressure");
			
			meteo.setTemp(temp);
			meteo.setFeels_like(feels_like);
			meteo.setTemp_min(temp_min);
			meteo.setTemp_max(temp_max);
			meteo.setPressure(pressure);
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		*/
		return meteo;	
	}
	
	
	
	
}
