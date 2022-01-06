package univpm.OpenWeather.Controller;



import java.net.MalformedURLException;




import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
	
	@Autowired
	WeatherImpl service;
	
	@GetMapping(value = "/ApiCall")	
	public ResponseEntity<Object> ApiCall(@RequestParam (name="name", defaultValue = "Milano") String name) throws MalformedURLException {
		return new ResponseEntity<>(service.getInfo(service.UrlBuilder(name)).toString(), HttpStatus.OK);
	}
	
	/*
	 * Metodo che permette di vedere le informazioni 
	 * relative alla città (non il meteo)
	 */
	
	@GetMapping (value = "/DailyWeather")
	public ResponseEntity<Weather> CityInfo(@RequestParam(name = "name", defaultValue = "Milano") String name) throws MalformedURLException {
		service.ResetUrl();
		return new ResponseEntity<>(service.getDailyWeather(name), HttpStatus.OK);
	}
	
	/*
	 * Metodo che consente di vedere le informazioni 
	 * riguardanti il meteo di un giorno di una città specifica
	 * @Author Francesco Rachiglia
	 */
	@GetMapping(value= "/CurrentWeather")
	public ResponseEntity<Weather> CurrentWeather(@RequestParam(name = "name", defaultValue = "Milano") String name) throws MalformedURLException{
		service.ResetUrl();
		return new ResponseEntity<>(service.getWeather(name), HttpStatus.OK);
	}
	
	
	
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
	
	
}

