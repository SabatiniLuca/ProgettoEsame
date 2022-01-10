package univpm.OpenWeather.Utils;

import org.json.simple.JSONObject;

import univpm.OpenWeather.Model.City;
import univpm.OpenWeather.Model.Position;
import univpm.OpenWeather.Model.Weather;
import univpm.OpenWeather.Service.WeatherImpl;

public class Stats implements StatsInt{
	
	WeatherImpl service = new WeatherImpl();
	
	public Weather getDailyWeather(JSONObject jobj , Weather meteo) {
		GetFromCall p=new GetFromCall();
		
		
		meteo.setDate(p.getLong(p.getDate(jobj),"dt"));
		meteo.setTemp(p.getDouble(p.getMain(jobj, true), "temp"));
		meteo.setTemp_min(p.getDouble(p.getMain(jobj, true), "temp_max"));
		meteo.setTemp_max(p.getDouble(p.getMain(jobj, true), "temp_min"));
		//meteo.setPressure(p.getDouble(p.getMain(jobj,true), "pressure"));
		meteo.setDescription(p.getString(p.getCity(jobj), "description"));
		meteo.setMain(p.getString(p.getCity(jobj), "main"));
		
		
		/*
		long date=(long) jobj.get("dt");//valorizza la data
		meteo.setDate(date);
		
		
		JSONObject tempObject=(JSONObject) jobj.get("main");//valorizza le temperature
		double temp = (double) tempObject.get("temp"); 
		double temp_max= (double) tempObject.get("temp_max");
		double temp_min= (double) tempObject.get("temp_min");
		meteo.setTemp(temp);
		meteo.setTemp_min(temp_min);
		meteo.setTemp_max(temp_max);
		
		long pressure=(long) tempObject.get("pressure");//valorizza la pressione
		meteo.setPressure(pressure);
		
		String description = service.searchArray(jobj, "weather" , "description");//valorizza la descrizione del meteo
		meteo.setDescription(description);
		String main = service.searchArray(jobj, "weather" , "main");//valorizza la descrizione del meteo
		meteo.setMain(main);
		*/				
		return meteo;	
	}
	
	
	public City getInfoCity(JSONObject jobj, City city) {
		//getFromCall p=new getFromCall();
				
		Position coord = new Position();
		JSONObject coordObj=(JSONObject) jobj.get("coord");//valorizza lon e lat
		double lon= (double) coordObj.get("lon");
		double lat= (double) coordObj.get("lat");
		coord.setLatitude(lat);
		coord.setLongitude(lon);
		city.setCoordinates(coord);
		
		String name=(String) jobj.get("name");//valorizza name e id
		long id=(long) jobj.get("id");
		city.setCityName(name);
		city.setId(id);	
		
		return city;	
	}
	
	
	
}
