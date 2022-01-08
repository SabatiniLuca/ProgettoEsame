package univpm.OpenWeather.Service;

import java.io.InputStreamReader;

import java.io.Reader;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.Iterator;
import java.util.Vector;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import univpm.OpenWeather.Model.City;
import univpm.OpenWeather.Model.Position;
import univpm.OpenWeather.Model.Weather;
import univpm.OpenWeather.Utils.Stats;
import univpm.OpenWeather.Utils.Utils;


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
		public City getCity(String cityName, Weather meteo) throws MalformedURLException {
			// TODO Auto-generated method stub
				
				ResetUrl();
				String u = UrlBuilder(true, cityName);//
				
				System.out.println(u);
				
				JSONObject object = getInfo(u);
				
				System.out.println(object);
				Stats s = new Stats();
				
				s.getInfoCity(object, meteo);
					
				return meteo;
		}

		@Override
		public Weather getWeather(String cityName, Weather meteo) throws MalformedURLException {
			// TODO Auto-generated method stub
			
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
		
		public Vector<Weather> getForecast(Vector<Weather> forecast,String cityName) throws MalformedURLException{
			Stats s=new Stats();
			Vector<Weather> fullForecast=new Vector<Weather>();
			
			ResetUrl();
			String u = UrlBuilder(false, cityName);
			
			JSONObject object = getInfo(u);//ottiene il JSONObject con tutte le previsioni
			
			JSONArray list=(JSONArray) object.get("list");//seleziono l'array contenente le informazioni del meteo
			Iterator<JSONObject> i=list.iterator();//creo un iteratore
			while(i.hasNext()) {
				
				Weather meteo=new Weather();//crea un oggetto Weather
				s.getDailyWeather(i.next(), meteo);//prende le informazioni relative al giorno iterato da i dentro il JSONArray list
				//meteo.setCity(city);//setta le informazioni della citta
				//meteo.setCoordinates(city.getCoordinates());
				fullForecast.add(meteo);
			}
			
			
			return fullForecast;
		}
		
		@SuppressWarnings("unchecked")// se lo tolgo si riempe di warnings perchè dice di definire il tipo di mappa
		@Override
		public JSONObject printInfo(Weather meteo) {
			Utils u=new Utils();
			
			JSONObject cityInfo=new JSONObject();
			
			JSONObject coordObj=new JSONObject();
			coordObj.put("lon",meteo.getCoordinates().getLongitude());
			coordObj.put("lat", meteo.getCoordinates().getLatitude());
			cityInfo.put("Coordinates", meteo.getCoordinates());
			
			JSONObject info=new JSONObject();
			info.put("Name", meteo.getCityName());
			info.put("Id", meteo.getId());
			cityInfo.put("info", info);
			
			JSONObject weather=new JSONObject();
			weather.put("Weather", meteo.getMain());
			weather.put("Specific", meteo.getDescription());
			cityInfo.put("Status", weather);
			
			JSONObject temp=new JSONObject();
			temp.put("Minimum", (u.tempConverter(meteo.getTemp_min())+" °C"));
			temp.put("Current", (u.tempConverter(meteo.getTemp())+" °C"));
			temp.put("Maximum", (u.tempConverter(meteo.getTemp_max())+" °C"));
			cityInfo.put("Temperatures", temp);
			
			cityInfo.put("Pressure", meteo.getPressure());
			
			cityInfo.put("dt", u.dateConverter(meteo.getDate()));
			return cityInfo;
		}

		
		@SuppressWarnings("unchecked")
		public JSONObject printInfoCity(City city) {
			Utils u=new Utils();
			
			JSONObject cityInfo=new JSONObject();
			
			JSONObject coordObj=new JSONObject();
			coordObj.put("lon",city.getCoordinates().getLongitude());
			coordObj.put("lat", city.getCoordinates().getLatitude());
			cityInfo.put("Coordinates", city.getCoordinates());
			
			JSONObject info=new JSONObject();
			info.put("Name", city.getCityName());
			info.put("Id", city.getId());
			cityInfo.put("info", info);
			return cityInfo;
		}
		
		@SuppressWarnings("unchecked")
		public JSONObject printInfoWeather(Weather meteo) {
			Utils u=new Utils();
			
			JSONObject cityWeather=new JSONObject();
			
			JSONObject weather=new JSONObject();
			weather.put("Weather", meteo.getMain());
			weather.put("Specific", meteo.getDescription());
			
			
			JSONObject temp=new JSONObject();
			temp.put("Minimum", (u.tempConverter(meteo.getTemp_min())+" °C"));
			temp.put("Current", (u.tempConverter(meteo.getTemp())+" °C"));
			temp.put("Maximum", (u.tempConverter(meteo.getTemp_max())+" °C"));
			cityWeather.put("Temperatures", temp);
			
			cityWeather.put("Pressure", meteo.getPressure());
			
			cityWeather.put("dt", u.dateConverter(meteo.getDate()));
			
			return cityWeather;
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
