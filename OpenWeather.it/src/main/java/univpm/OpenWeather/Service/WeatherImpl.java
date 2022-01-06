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
		
		/**
		 * ho modificato l'url iniziale per poter usare @method UrlBuilder correttamente 
		 * @author lucas
		 */
		private String apiKey = "15b8b402dfd9f2d93b1bfa8245d0edc6";
		private String url ="https://api.openweathermap.org/data/2.5/";
		
		/**
		 * Questo metodo setta la stringa url 
		 * @author lucas
		 */
		@Override 
		public String UrlBuilder(boolean current, String cityName) {
			//creazione Url
			
			if(current==true) {//current weather
				this.url+="weather?q="+cityName+"&appid="+this.apiKey;  
			}
			else if(current==false) {//5day forecast
				this.url+="forecast?q="+cityName+"&appid="+this.apiKey;
			}
			return this.url;
		}
		
		/**
		 * questo metodo crea una connessione usando il parametro u e restituisce un Json object
		 */
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
			City city = new City();
			
			ResetUrl();
			String u = UrlBuilder(true, cityName);//
		
			JSONObject object = getInfo(u);
			
			Stats s = new Stats();
			
			s.getInfoCity(object, city);
			
			return city;
		
		}


		public Weather getWeather(String cityName) throws MalformedURLException {
			// TODO Auto-generated method stub
			Weather meteo = new Weather();
			
			ResetUrl();
			String u = UrlBuilder(true, cityName); //Crea URL
			
			JSONObject object = getInfo(u); //JSONObject contentente il JSON 
			
			
			Stats s = new Stats();
			/**
			 * @method getInfoCity prende le informazioni riguardanti: coordinate,nome, id
			 * @method getDailyWeather prende le indformazioni riguardanti: temperature, pressioni,descrizione meteo
			 * @author lucas
			 */
			//s.getInfoCity(object, meteo);
			s.getDailyWeather(object, meteo); //Passo il JSON per farlo elaborare
					
			return meteo;
		}
		
		
		@SuppressWarnings("unchecked")// se lo tolgo si riempe di warnings perchè dice di definire il tipo di mappa
		@Override
		public JSONObject printInfo(Weather city) {
			
			JSONObject cityInfo=new JSONObject();
			
			JSONObject coord=new JSONObject();
			coord.put("lon",city.getLongitude());
			coord.put("lat", city.getLatitude());
			cityInfo.put("Coordinates", coord);
			
			JSONObject info=new JSONObject();
			info.put("Name", city.getCityName());
			info.put("Id", city.getId());
			cityInfo.put("info", info);
			
			JSONObject weather=new JSONObject();
			//weather.put("Weather", city.getMain());
			weather.put("Specific", city.getDescription());
			cityInfo.put("Status", weather);
			
			JSONObject temp=new JSONObject();
			temp.put("Minimum", city.getTemp_min());
			temp.put("Current", city.getTemp());
			temp.put("Maximum", city.getTemp_max());//(city.getTemp_max() − 32) × 5/9 
			cityInfo.put("Temperatures", temp);
			
			cityInfo.put("Pressure", city.getPressure());
			cityInfo.put("Date", city.getData());
			
			return cityInfo;
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
			this.url = "https://api.openweathermap.org/data/2.5/";
		}


		
}
