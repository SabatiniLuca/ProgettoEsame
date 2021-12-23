package univpm.OpenWeather.Controller;

	

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;



@RestController
public class OpenWeatherController {
		
		
	@RequestMapping("/Weather")	
	public StringBuilder ApiCall() throws MalformedURLException{
		
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
				JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));
				
				System.out.println(dataObject.get(0));
				
				JSONObject countryData = (JSONObject) dataObject.get(0);
				
				System.out.println(countryData.get("location type"));
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return informationString;
		
	}

}
