package univpm.OpenWeather.Service;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import univpm.OpenWeather.Model.City;
import univpm.OpenWeather.Model.Position;
import univpm.OpenWeather.Model.Weather;
import univpm.OpenWeather.Utils.Stats;


	@Service
	public class WeatherImpl implements WeatherInt {
		
		private String apiKey = "15b8b402dfd9f2d93b1bfa8245d0edc6";
		private String url ="https://api.openweathermap.org/data/2.5/forecast?q=";
		
		@Override 
		public String UrlBuilder(String name) {
			if (name.isEmpty()) {
				return null;
			}
			else {
				this.url= url + name + "&appid=" + apiKey;
			}
			return this.url;						
		}
		
		
		@Override
		public JSONObject getInfo(String u) throws MalformedURLException {
			// TODO Auto-generated method stub
			JSONObject obj = null;
			URL url = new URL(u);
				try {
					
					HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.connect();
				
					int responseCode = conn.getResponseCode();
					if (responseCode !=200) {
						throw new Exception("HttpResponseCode: " + responseCode);
					}else {
						Reader scan=new InputStreamReader(url.openStream());
					
						JSONParser parser = new JSONParser();
						
						obj = (JSONObject) parser.parse(scan);
										
						scan.close();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				return obj;
			
	}
		
		
		@Override
		public City getCity(String cityName) throws MalformedURLException {
			// TODO Auto-generated method stub
				String u = UrlBuilder(cityName);
			
				JSONObject object = getInfo(u);
				
				City city = new City();
				Position coordinates = new Position();
				
				try {
					JSONObject cityObj = (JSONObject) object.get("city");
					String name = (String) cityObj.get("name");
					String country = (String) cityObj.get("country");
					long id = (long) cityObj.get("id");
					city.setCityName(name);
					city.setId(id);
					
					JSONObject coordinatesObj = (JSONObject) cityObj.get("coord");
					double lat = (double) coordinatesObj.get("lat");
					double lon = (double) coordinatesObj.get("lon");
					coordinates.setLatitude(lat);
					coordinates.setLongitude(lon);
					city.setCoordinates(coordinates);
				
					
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(city.getCityName() + " " + city.getId()) ;
				
				return city;
			
		}


		public Weather getWeather(String cityName) throws MalformedURLException {
			// TODO Auto-generated method stub
			Weather meteo = new Weather();
			
			String u = UrlBuilder(cityName); //Crea URL
			
			JSONObject object = getInfo(u); //JSONObject contentente il JSON 
			
			Stats s = new Stats();
			s.getDailyWeather(object, meteo); //Passo il JSON per farlo elaborare
					
			return meteo;
		}

		/**
		 *Metodo che usa getCity per prendere previsioni 
		 *meteo della città richiesta e
		 *restituisce il JSONArray
		 * 
		 *@return restituisce il JSONArray con la città e le relative informazioni
		 * 
		 */
		
		public String searchArray(JSONObject obj,String arrayName, String valueName) {
			JSONArray array=(JSONArray) obj.get(arrayName);
			Iterator<?> i=array.iterator();
			String value="";
			
			while (i.hasNext()) {
				JSONObject info=(JSONObject) i.next();
				value=(String) info.get(valueName);
			}
			return value;
			
		}
		
		
		public void ResetUrl() {
			this.url = "https://api.openweathermap.org/data/2.5/forecast?q=";
		}


		
}
