package univpm.OpenWeather.Controller;


import java.net.MalformedURLException;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import univpm.OpenWeather.Model.Weather;
import univpm.OpenWeather.Service.WeatherImpl;

@RestController
public class OpenWeatherController {
	//private boolean current=true;
	
	@Autowired
	WeatherImpl service;
	
	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value="name",defaultValue="Mario")String name) {
		return "Salve "+name+", questo è OpenWeather";		
	}
	
	/**
	 * cambio solo queste prime due rotte
	 * @param name nome della città
	 * @return JSONObject con la rappresentazione della città
	 * @throws MalformedURLException avvisa se la chiamata non è andata a buon fine
	 * 
	 * @author lucas
	 */
	
	@RequestMapping("/current")
	public ResponseEntity<JSONObject> current(@RequestParam(name = "name", defaultValue = "Milano")String name) throws MalformedURLException{
		Weather meteo=new Weather();
		service.getCity(name, meteo);
		service.getWeather(name, meteo);
		return new ResponseEntity<>(service.printInfo(meteo, true), HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/forecast")
	public ResponseEntity<Vector<Weather>> forecast(@RequestParam(name = "name", defaultValue = "Milano")String name) throws MalformedURLException{
		
		Vector<Weather> forecast=new Vector<Weather>();
		forecast=service.getForecast(forecast, name);
		JSONObject all=new JSONObject();
		
		
		for(Weather w:forecast) {
			all.put("day",service.printInfo(w, true));
		}
		return new ResponseEntity<>(forecast, HttpStatus.OK);
	}
	
	@GetMapping("/saveEveryHour")
	public ResponseEntity<String> saveEveryHour(@RequestParam String name){
		Weather weather = new Weather();
		String path = service.saveHourlyWeather(name, weather);
		return new ResponseEntity<>(path, HttpStatus.OK);
	}
	
	
	
	/*
	 * da qui in giù non ho modificato niente
	 
	
	
	
	@GetMapping(value = "/ApiCall")	
	public ResponseEntity<Object> ApiCall(@RequestParam (name="name", defaultValue = "Milano") String name) throws MalformedURLException {
		return new ResponseEntity<>(service.getInfo(service.UrlBuilder(name)).toString(), HttpStatus.OK);
	}
	
	/*
	 * Metodo che permette di vedere le informazioni 
	 * relative alla città (non il meteo)
	 *
	
	@GetMapping (value = "/CityInfo")
	public ResponseEntity<City> CityInfo(@RequestParam(name = "name", defaultValue = "Milano") String name) throws MalformedURLException {
		service.ResetUrl();
		return new ResponseEntity<>(service.getCity(name), HttpStatus.OK);
	}
	
	/*
	 * Metodo che consente di vedere le informazioni 
	 * riguardanti il meteo di un giorno di una città specifica
	 * @Author Francesco Rachiglia
	 *
	
	
	@RequestMapping("/weather")
	public StringBuilder Meteo() throws MalformedURLException {
		
		StringBuilder informationString = new StringBuilder();
		URL url= new URL("https://api.openweathermap.org/data/2.5/forecast?q=Milano&appid=15b8b402dfd9f2d93b1bfa8245d0edc6&mode=JSON&units=metric&lang=it");
		
		
		try {
			HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
		
			int responseCode = conn.getResponseCode();
			if (responseCode !=200) {
				throw new Exception("HttpResponseCode: " + responseCode);
			}else {
				Scanner scan=new Scanner(url.openStream());
				informationString = new StringBuilder();
				while(scan.hasNext()) {
					informationString.append(scan.nextLine());
				}
				scan.close();
				System.out.println(informationString);
			
				JSONParser parse = new JSONParser();
				JSONArray ris = (JSONArray) parse.parse(String.valueOf(informationString));
			
				System.out.println(ris.get(0));
			
				JSONObject countryData = (JSONObject) ris.get(0);
			
				System.out.println(countryData.get("location_type"));
			
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return informationString;
	}
	*/
	
	
}

