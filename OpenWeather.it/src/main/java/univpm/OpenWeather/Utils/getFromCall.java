package univpm.OpenWeather.Utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import univpm.OpenWeather.Model.City;
import univpm.OpenWeather.Model.Position;
import univpm.OpenWeather.Model.Weather;
import univpm.OpenWeather.Service.WeatherImpl;

public class getFromCall {

	public getFromCall() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	@SuppressWarnings("unchecked")
	public JSONObject getCurrent(JSONObject obj) {
		JSONObject toPrint=new JSONObject();
		toPrint.put("City", getCity(obj));
		toPrint.put("Coord", getCoord(obj));
		toPrint.put("Weather", getWeather(obj));
		//toPrint.put("Temps", getTemp((JSONObject) obj.get("main"),true));
		//toPrint.put("Pressure", getPressure((JSONObject) obj.get("main")));
		return toPrint;
	}
	
	public String getString(JSONObject obj, String value) {
		String ret=(String) obj.get(value);
		return ret;
	}
	
	public double getDouble(JSONObject obj, String value) {
		double ret=(double) obj.get(value);
		return ret;
	}
	
	public long getLong(JSONObject obj, String value) {
		long ret=(long) obj.get(value);
		return ret;
	}
	
	public Position createPosition(JSONObject obj) {
		JSONObject p=getCoord(obj);
		double lat=(double) p.get("lat");
		double lon=(double) p.get("lon");
		Position pos=new Position(lat, lon);
		return pos;
	}
	
	public City createCity(JSONObject obj) {
		JSONObject c=getCity(obj);
		System.out.println(c);
		long id=(long) c.get("Id");
		String name=(String) c.get("City Name");
		Position pos=createPosition(obj);
		City city=new City(id,name,pos);
		System.out.println(city);
		return city;
	}
	
	public Weather createWeather(JSONObject obj,boolean current) {
		
			JSONObject t=getMain(obj,true);
			JSONObject w=getWeather(obj);
			//System.out.println(t);
			//System.out.println(w);
			double temp=(double) t.get("t");
			String description= (String) w.get("description");
			String main= (String) w.get("main");
			double t_min=(double) t.get("tMin");
			double t_max=(double) t.get("tMax");
			long date=(long) obj.get("dt");
			long pressure=(long) t.get("pressure");
			
			
			
			//meteo.setCity(city);
		if(current) {	
			City city=new City();
			city=createCity(obj);
			Weather meteo=new Weather(temp, description,t_min,t_max,pressure,date, main,city);
			return meteo;
		}else {
			Weather meteo1=new Weather(temp, description,t_min,t_max,pressure,date, main);
			return meteo1;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public JSONObject getCity(JSONObject obj) {
		JSONObject ret=new JSONObject();
		String name=(String) obj.get("name");//valorizza name e id
		long id=(long) obj.get("id");
		ret.put("City Name", name);
		ret.put("Id", id);
		
		return ret;
		
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getCoord(JSONObject obj) {
		JSONObject coordObj=(JSONObject) obj.get("coord");//valorizza lon e lat
		double lon= (double) coordObj.get("lon");
		double lat= (double) coordObj.get("lat");
		coordObj.put("lon", lon);
		coordObj.put("lat", lat);
		
		return coordObj;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getWeather(JSONObject obj) {
		WeatherImpl service=new WeatherImpl();
		JSONArray ar=(JSONArray) obj.get("weather");
		JSONObject weather=(JSONObject) ar.get(0);
		String description = service.searchArray(obj, "weather" , "description");
		String main = service.searchArray(obj, "weather" , "main");
		weather.put("Main", main);
		weather.put("Description", description);
		
		return weather;
		
	}
	
	public Weather setWeather(JSONObject obj) {
		WeatherImpl service=new WeatherImpl();
		Weather meteo=new Weather();
		//long date=(long) obj.get("dt");
		//meteo.setDate(date);
		meteo.setDescription((String) obj.get("Description"));
		meteo.setMain((String) obj.get("Main"));
		JSONObject main=(JSONObject) obj.get("main");
		meteo.setTemp((double)  main.get("temp")) ;
		meteo.setPressure((long) main.get("pressure"));
		String description = service.searchArray(obj, "weather" , "description");
		String mainMeteo = service.searchArray(obj, "weather" , "main");
		meteo.setDescription(description);
		meteo.setMain(mainMeteo);
		
		return meteo;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public JSONObject getMain(JSONObject obj,boolean current) {
		JSONObject m=(JSONObject) obj.get("main");
		JSONObject ret= new JSONObject();
		if(current) {
			double temp = (double) m.get("temp"); 
			double temp_max= (double) m.get("temp_max");
			double temp_min= (double) m.get("temp_min");
			ret.put("t", temp);
			ret.put("tMax", temp_max);
			ret.put("tMin", temp_min);
		}else {
			double temp = (double) m.get("temp");
			ret.put("temp", temp);
		}
		
		long pressure=(long) m.get("pressure");
		
		ret.put("pressure", pressure);
		
		
		return ret;		
	}
	
	
	
	
	
	
	public JSONObject getDate(JSONObject obj) {
		return (JSONObject) obj.get("dt");
	}
	
	

}
