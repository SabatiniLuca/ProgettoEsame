package univpm.OpenWeather.Utils;

import org.json.simple.JSONObject;

import univpm.OpenWeather.Service.WeatherImpl;

public class getFromCall {

	public getFromCall() {
		// TODO Auto-generated constructor stub
	}
	
	WeatherImpl service;
	
	@SuppressWarnings("unchecked")
	public JSONObject getCurrent(JSONObject obj) {
		JSONObject toPrint=new JSONObject();
		toPrint.put("City", getCity(obj));
		toPrint.put("Coord", getCoord(obj));
		toPrint.put("Weather", getWeather(obj));
		toPrint.put("Temps", getTemp((JSONObject) obj.get("main"),true));
		toPrint.put("Pressure", getPressure((JSONObject) obj.get("main")));
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
	
	
	@SuppressWarnings("unchecked")
	public JSONObject getCity(JSONObject obj) {
		JSONObject ret=new JSONObject();
		String name=(String) obj.get("name");//valorizza name e id
		long id=(long) obj.get("id");
		ret.put("City Name", name);
		ret.put("Id", id);
		
		return ret;
		
	}
	
	public JSONObject getCoord(JSONObject obj) {
		JSONObject coordObj=(JSONObject) obj.get("coord");//valorizza lon e lat
		double lon= (double) coordObj.get("lon");
		double lat= (double) coordObj.get("lat");
		
		return coordObj;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getWeather(JSONObject obj) {
		JSONObject weather=new JSONObject();
		String description = service.searchArray(obj, "weather" , "description");
		String main = service.searchArray(obj, "weather" , "main");
		weather.put("Main", main);
		weather.put("Description", description);
		
		return weather;
		
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getTemp(JSONObject obj,boolean current) {
		JSONObject t=new JSONObject();
		if(current) {
			double temp = (double) obj.get("temp"); 
			double temp_max= (double) obj.get("temp_max");
			double temp_min= (double) obj.get("temp_min");
			t.put("t", temp);
			t.put("tMax", temp_max);
			t.put("tMin", temp_min);
		}else {
			double temp = (double) obj.get("temp");
			t.put("temp", temp);
		}
		
		return t;		
	}
	
	public JSONObject getPressure(JSONObject obj) {
		return (JSONObject) obj.get("pressure");
	}
	
	public JSONObject getDate(JSONObject obj) {
		return (JSONObject) obj.get("dt");
	}
	
	

}
