package univpm.OpenWeather.Utils;

//import java.sql.Date;
import java.text.SimpleDateFormat;

import org.json.simple.JSONObject;

import univpm.OpenWeather.Model.City;
import univpm.OpenWeather.Model.Position;
import univpm.OpenWeather.Model.Weather;
import univpm.OpenWeather.Service.WeatherImpl;

public class Stats implements StatsInt{
	
	WeatherImpl service = new WeatherImpl();
	
	public Weather getDailyWeather(JSONObject jobj, Weather meteo) {
		
		//System.out.println(jobj);
		
		JSONObject tempObject=(JSONObject) jobj.get("main");//valorizza le temperature
		double temp =(double) tempObject.get("temp"); 
		double temp_max= (double) tempObject.get("temp_max");
		double temp_min= (double) tempObject.get("temp_min");
		meteo.setTemp(temp);
		meteo.setTemp_min(temp_min);
		meteo.setTemp_max(temp_max);
		
		long pressure=(long) tempObject.get("pressure");//valorizza la pressione
		meteo.setPressure(pressure);
		
		String description = service.searchArray(jobj, "weather" , "description");//valorizza la descrizione del meteo
		meteo.setDescription(description);
		/*String dateStr = obj.getString("birthdate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date birthDate = sdf.parse(dateStr);
		//then
		user.setBirthdate(birthDate);
		meteo.setDate(date);*/
		/**
		 * se vogliamo si potrebbe fare la stessa cosa anche per il campo
		 * "main" dentro l'array di weather (indica il nome del fenomeno atmosferico
		 * mentre description è piu dettagliato) 
		 * esempio
		 * main= rain
		 * description= light rain
		 */
				
				
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
	
	
	public City getInfoCity(JSONObject jobj, City meteo) {
		
		//System.out.println(jobj);
		Position coord = new Position();
		JSONObject coordObj=(JSONObject) jobj.get("coord");//valorizza lon e lat
		double lon= (double) coordObj.get("lon");
		double lat= (double) coordObj.get("lat");
		coord.setLatitude(lat);
		coord.setLongitude(lon);
		meteo.setCoordinates(coord);
		
		String name=(String) jobj.get("name");//valorizza name e id
		long id=(long) jobj.get("id");
		meteo.setCityName(name);
		meteo.setId(id);	
		
		return meteo;	
	}
	
	
	
}
