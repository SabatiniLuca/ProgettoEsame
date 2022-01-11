package univpm.OpenWeather.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.Reader;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.springframework.stereotype.Service;

import univpm.OpenWeather.Model.Weather;
import univpm.OpenWeather.Utils.Utils;
import univpm.OpenWeather.Utils.GetFromCall;


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
					
						JSONParser parser = new JSONParser() ;
						
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
		public Weather getWeather(String cityName, Weather meteo) throws MalformedURLException {
			// TODO Auto-generated method stub
			GetFromCall p=new GetFromCall();
			ResetUrl();
			String u = UrlBuilder(true, cityName); //Crea URL
			
			JSONObject object = getInfo(u); //JSONObject contentente il JSON 
			
			meteo=p.createWeather(object,true);
			
			
			/**
			 * @method getInfoCity prende le informazioni riguardanti: coordinate,nome, id
			 * @method getDailyWeather prende le indformazioni riguardanti: temperature, pressioni,descrizione meteo
			 * @author lucas
			 */
			
			return meteo;
		}
		
		
		
		
		
		@SuppressWarnings("unchecked")
		public JSONObject getForecast(String cityName) throws MalformedURLException{
			GetFromCall p=new GetFromCall();
			
			new HashMap<String,HashMap<String,JSONObject>>();
			
			ResetUrl();
			String u = UrlBuilder(false, cityName);
			
			JSONObject object = getInfo(u);//ottiene il JSONObject con tutte le previsioni
			
			
			
			JSONArray list=(JSONArray) object.get("list");//seleziono l'array contenente le informazioni del meteo
			Iterator<JSONObject> i=list.iterator();//creo un iteratore
			
			JSONArray ar=new JSONArray();
			Integer contatore=0;
			while(i.hasNext()) {
				//Date previous=null , next=null;
				//HashMap<String,JSONObject> day=new HashMap<String,JSONObject>();
				//do {
										
					//System.out.println(i.next());
					JSONObject temp=i.next();
					Weather meteo=p.createWeather(temp,false);
					JSONObject ob=printInfo(meteo,false);
					ob.put("Day ", contatore);
					ar.add(ob);
					contatore++;
					//day.put("Weather"+contatore, printInfo(meteo,false) );//prende le informazioni relative al giorno iterato da i dentro il JSONArray list
					
					
				//}while(previous!=null && DateUtils.isSameDay(previous, next));
				
				
				//forecast.put("day" + contatore, day);
					
			}
			
			
			JSONObject toPrint=new JSONObject();
			toPrint.put("City", object.get("city"));
			toPrint.put("Forecasts", ar);
			
			//City city=p.createCity(object);
			//toPrint.put("City", city);
			//toPrint.putAll(forecast);
			
			//System.out.println(toPrint);
			
			return toPrint;
		}
		
		
		
		
		/**
		 * questo metodo stampa tutte le informazioni se 
		 * @param all è true
		 * in caso contrario stampa solo le informazioni del meteo
		 * @author lucas
		 */
		@SuppressWarnings("unchecked")// se lo tolgo si riempe di warnings perchè dice di definire il tipo di mappa
		@Override
		public JSONObject printInfo(Weather meteo, boolean all) {
			Utils u=new Utils();
			JSONObject allInfo=new JSONObject();
			
			JSONObject cityInfo=new JSONObject();
			JSONObject weatherInfo=new JSONObject();
			
			if(all) {
				cityInfo.put("Coordinates", meteo.getCity().getCoordinates());
				cityInfo.put("Name", meteo.getCity().getCityName());
				cityInfo.put("Id", meteo.getCity().getId());
					
			}
			
			JSONObject weather=new JSONObject();
			weather.put("Weather", meteo.getMain());
			weather.put("Specific", meteo.getDescription());
			weatherInfo.put("Status", weather);
			
			JSONObject temp=new JSONObject();
			temp.put("Minimum", (u.tempConverter(meteo.getTemp_min())+" °C"));
			temp.put("Current", (u.tempConverter(meteo.getTemp())+" °C"));
			temp.put("Maximum", (u.tempConverter(meteo.getTemp_max())+" °C"));
			weatherInfo.put("Temperatures", temp);
			
			weatherInfo.put("Pressure", meteo.getPressure());
			
			weatherInfo.put("date", u.toDate(meteo.getDate()));
			
			allInfo.put("City", cityInfo);
			allInfo.put("Forecasts", weatherInfo);
			
			if(all) {
				return allInfo;
			}else {
				return weatherInfo;
			}
			
			
			
		}
		
		/**
		 * Metodo che salva su file le informazioni 
		 * del meteo di una città
		 * @param name(nome della città), weather(oggetto di Weather per prenderer tutte le informazioni sul meteo)
		 * @author Francesco
		 */
		
		public String saveHourlyWeather(String name, Weather weather) {
			
			String path = System.getProperty("user.dir") + "/" + name + "HourlyWeather";
			File file = new File(path);	
			
			ScheduledExecutorService eTP = Executors.newSingleThreadScheduledExecutor();
			System.out.println("Start Execution");
			
			eTP.scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
					
					JSONObject toFile = new JSONObject();
					Weather meteo = new Weather();
					try {
						//meteo = (Weather) getCity(name, weather);
						meteo = getWeather(name, weather);
						System.out.println("meteo " +meteo);
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(toFile);
					toFile = printInfo(meteo, true);
					
					System.out.println(toFile);
					try {
						if (!file.exists()) {
							file.createNewFile();
							System.out.println("File created");
						}
						FileWriter f = new FileWriter(file); 
						BufferedWriter n = new BufferedWriter(f);
						n.write(toFile.toString());
						n.close();
					}
					catch(IOException e)
					{
						System.out.println(e); //creare eccezioni
					}
			
				}
			}, 0, 3, TimeUnit.HOURS);
			
			return "File salvato in " + file;
}
			
		
		
		
		/**
		 *Metodo che usa getCity per prendere previsioni 
		 *meteo della città richiesta e
		 *restituisce il JSONArray
		 * 
		 *@return restituisce il JSONArray con la città e le relative informazioni
		 * 
		 */
		/*
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
		*/
		
		public void ResetUrl() {
			this.url = "https://api.openweathermap.org/data/2.5/";
		}


		
}
