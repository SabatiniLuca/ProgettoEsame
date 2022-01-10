package univpm.OpenWeather.Utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import univpm.OpenWeather.Model.City;
import univpm.OpenWeather.Model.Position;
import univpm.OpenWeather.Model.Weather;

/**
 * Questa classe usa diversi metodi per prendere le informazioni da un JSONObject e crea delle 
 * istanze delle rispettive classi. 
 * @author lucas
 */
public class GetFromCall {

	public GetFromCall() {
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
	
	/**
	 * crea una posizione da un JSONobject
	 * @param obj json object da cui prendere le informazioni di latitudine e longitudine
	 * @return un oggetto della classe Position
	 * @author lucas
	 */
	public Position createPosition(JSONObject obj) {
		JSONObject p=getCoord(obj);
		double lat=(double) p.get("lat");
		double lon=(double) p.get("lon");
		Position pos=new Position(lat, lon);
		return pos;
	}
	
	/**
	 * Questo metodo consente di creare una citta da un Json object
	 * @param obj Json object da elaborare
	 * @return ritorna un oggetto della classe City
	 * @author lucas
	 */
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
	
	/**
	 * questo metodo consente di creare un oggetto della classe Weather da un json object.
	 * inoltre valorizza solo il meteo oppure anche gli attributi della citta in base al parametro
	 * booleano poichè in alcuni casi gli attributi di citta rimangono gli stessi e non c'è bisogno di ricrearli.
	 * 
	 * Grazia al concatenamento con createCity  e createPosition con questa classe si possono valorizzare anche gli attributi City e Position.
	 * 
	 * @param obj Json object da elaborare
	 * @param current seleziona il tipo di operazione da fare
	 * @return un oggetto della classe Weather valorizzato con il json object passatogli
	 */
	public Weather createWeather(JSONObject obj,boolean current) {
			JSONObject t=(JSONObject) obj.get("main");
			//JSONObject t=getMain(obj,current);
			
			JSONObject w=getWeather(obj);
			
			double temp=(double) t.get("temp");
			//System.out.println(t);
			double t_min=(double) t.get("temp_min");
			double t_max=(double) t.get("temp_max");
			long pressure=(long) t.get("pressure");
			String description= (String) w.get("description");
			String main= (String) w.get("main");
			long date=(long) obj.get("dt");
			
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
	public JSONObject getMain(JSONObject obj,boolean current) {
		JSONObject m=(JSONObject) obj.get("main");
		JSONObject ret= new JSONObject();
		
		double temp = (double) m.get("temp"); 
		double temp_max= (double) m.get("temp_max");
		double temp_min= (double) m.get("temp_min");
		if(current) {
			
			ret.put("t", temp);
			ret.put("tMax", temp_max);
			ret.put("tMin", temp_min);
		}else {
			//double temp = (double) m.get("temp");
			ret.put("temp", temp);
		}
		
		long pressure=(long) m.get("pressure");
		
		ret.put("pressure", pressure);
		
		
		return ret;		
	}
	
	/**
	 * crea un JSONObject le informazioni di citta
	 * @param obj
	 * @return json object
	 * @author lucas
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getCity(JSONObject obj) {
		JSONObject ret=new JSONObject();
		String name=(String) obj.get("name");//valorizza name e id
		long id=(long) obj.get("id");
		ret.put("City Name", name);
		ret.put("Id", id);
		
		return ret;
		
	}
	/**
	 * crea un jsonobject con informazioni delle coordinate
	 * @param obj
	 * @return json object
	 * @author lucas
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getCoord(JSONObject obj) {
		JSONObject coordObj=(JSONObject) obj.get("coord");//valorizza lon e lat
		double lon= (double) coordObj.get("lon");
		double lat= (double) coordObj.get("lat");
		coordObj.put("lon", lon);
		coordObj.put("lat", lat);
		
		return coordObj;
	}
	/**
	 * 
	 * @param obj
	 * @return
	 * @author lucas
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getWeather(JSONObject obj) {
		Utils u=new Utils();
		JSONArray ar=(JSONArray) obj.get("weather");
		JSONObject weather=(JSONObject) ar.get(0);
		String description = u.searchArray(obj, "weather" , "description");
		String main = u.searchArray(obj, "weather" , "main");
		weather.put("Main", main);
		weather.put("Description", description);
		
		return weather;
		
	}
	
	
	/*
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
	*/
	
	
	/**
	 * 
	 * @param obj
	 * @param current
	 * @return
	 * @author lucas
	 */
	
	
	
	
	
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	public JSONObject getDate(JSONObject obj) {
		return (JSONObject) obj.get("dt");
	}
	
	

}