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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import univpm.OpenWeather.Service.WeatherImpl;



@RestController
public class OpenWeatherController {
	
	@Autowired
	WeatherImpl service;
		
		
	@GetMapping(value = "/ApiCall")	
	public ResponseEntity<JSONObject> ApiCall(@RequestParam String cityName) throws MalformedURLException {
		return new ResponseEntity<>(service.getAPI(cityName), HttpStatus.OK);
	}

	
	@GetMapping (value = "/Weather")
	public StringBuilder Meteo() {
		StringBuilder informationString =new StringBuilder();
	
		try {
			URL openweather= new URL("https://api.openweathermap.org/data/2.5/forecast?q=Milano&appid=15b8b402dfd9f2d93b1bfa8245d0edc6&mode=JSON&units=metric&lang=it");
			HttpsURLConnection conn=(HttpsURLConnection) openweather.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
		
			int responseCode = conn.getResponseCode();
			if (responseCode !=200) {
				throw new Exception("HttpResponseCode: " + responseCode);
			}else {
				Scanner scan=new Scanner(openweather.openStream());
				while(scan.hasNext()) {
					informationString.append(scan.nextLine());
				}
				scan.close();
				System.out.println(informationString);
			
				JSONParser parse = new JSONParser();
				JSONArray ris = (JSONArray) parse.parse(String.valueOf(informationString));
			
				System.out.println(ris.get(0));
			
				JSONObject countryData = (JSONObject) ris.get(0);
			
				System.out.println(countryData.get("location type"));
			
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return informationString;
	
		
	}
}
