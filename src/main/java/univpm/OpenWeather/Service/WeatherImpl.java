package univpm.OpenWeather.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class WeatherImpl implements WeatherInt {
	
	private String apiKey = "15b8b402dfd9f2d93b1bfa8245d0edc6";
	
	@Override
	public JSONObject getCity(String cityName) {
		// TODO Auto-generated method stub
		JSONObject name = new JSONObject();
	/*	String url = "https://api.openweathermap.org/data/2.5/forecast?q="+ cityName + "&appid" + apiKey ;
		
		RestTemplate prova = new RestTemplate();
		
		name = new JSONObject(prova.getForObject(url, String.class));
	*/
		
		return name;
		
	}

	/**
	 *Metodo che usa getCity per prendere previsioni 
	 *meteo della città richiesta e
	 *restituisce il JSONArray
	 * 
	 *@return restituisce il JSONArray con la città e le relative informazioni
	 * 
	 */
	
	
	
	@Override
	public JSONArray getAPI(String name) throws MalformedURLException {
		// TODO Auto-generated method stub
		StringBuilder informationString =new StringBuilder();
		JSONArray ris = new JSONArray();
		
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
					ris = (JSONArray) parse.parse(String.valueOf(informationString));
				
					System.out.println(ris.get(0));
				
					JSONObject countryData = (JSONObject) ris.get(0);
				
					System.out.println(countryData.get("location type"));
				
				}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return ris;
		
}
	
	@Override 
	public String toString() {
		return null;
	}


	

}
