package univpm.OpenWeather.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.Reader;
import java.net.MalformedURLException;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.springframework.stereotype.Service;

import univpm.OpenWeather.Exception.NullObjectException;
import univpm.OpenWeather.Model.Weather;
import univpm.OpenWeather.Utils.Utils;
import univpm.OpenWeather.Utils.GetFromCall;
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
				this.url+="weather?q="+cityName+",IT"+"&appid="+this.apiKey;  
			}
			else if(current==false) {//5day forecast
				this.url+="forecast?q="+cityName+",IT"+"&appid="+this.apiKey;
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
		public Weather getWeather(String cityName) throws MalformedURLException {//, Weather meteo
			// TODO Auto-generated method stub
			GetFromCall p=new GetFromCall();
			ResetUrl();
			String u = UrlBuilder(true, cityName); //Crea URL
			
			JSONObject object = getInfo(u); //JSONObject contentente il JSON 
			Weather meteo=p.createWeather(object,true);
			
			
			/**
			 * @method getInfoCity prende le informazioni riguardanti: coordinate,nome, id
			 * @method getDailyWeather prende le indformazioni riguardanti: temperature, pressioni,descrizione meteo
			 * @author lucas
			 */
			
			return meteo;
		}
		
		
		@SuppressWarnings("unchecked")
		public JSONObject getForecast(String cityName) throws MalformedURLException, ParseException {
			GetFromCall p = new GetFromCall();
			
			ResetUrl();
			String u = UrlBuilder(false, cityName);

			JSONObject object = getInfo(u);// ottiene il JSONObject con tutte le previsioni

			JSONArray list = (JSONArray) object.get("list");// seleziono l'array contenente le informazioni del meteo
			Iterator<JSONObject> i = list.iterator();// creo un iteratore

			JSONObject toPrint = new JSONObject();
			JSONArray dayArray = new JSONArray();
			
			while (i.hasNext()) {
				
				JSONObject temp = i.next();
				Weather meteo = p.createWeather(temp, false);

				dayArray.add(printInfo(meteo, false));

			}

			toPrint.put("City", object.get("city"));
			toPrint.put("Forecasts", dayArray);
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
			//info stampate se all é true
			
			cityInfo.put("Coordinates", meteo.getCity().getCoordinates());
			cityInfo.put("Name", meteo.getCity().getCityName());
			cityInfo.put("Id", meteo.getCity().getId());
			}		
			
			//info stampate se all é false
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");//  
            String strDate = dateFormat.format(u.toDate(meteo.getDate()));  
			weatherInfo.put("date", strDate);
			JSONObject weather=new JSONObject();
			weather.put("Weather", meteo.getMain());
			weather.put("Specific", meteo.getDescription());
			weather.put("Pressure", meteo.getPressure() +" Pa");
			weatherInfo.put("Status", weather);
			
			JSONObject temp=new JSONObject();
			temp.put("Minimum", (u.tempConverter(meteo.getTemp_min())+" °C"));
			temp.put("Current", (u.tempConverter(meteo.getTemp())+" °C"));
			temp.put("Maximum", (u.tempConverter(meteo.getTemp_max())+" °C"));
			weatherInfo.put("Temperatures", temp);
			
			
			
			
			
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
		 * @throws NullObjectException 
		 */		
		@SuppressWarnings("unchecked")
		public JSONObject saveHourlyWeather(String name,boolean stats) throws NullObjectException {
			
			
			String path = System.getProperty("user.dir") + "/" + name + "HourlyWeather.txt";
			File file = new File(path);	
			
			ScheduledExecutorService eTP = Executors.newSingleThreadScheduledExecutor();
			System.out.println("Start Execution");
			
			JSONObject toPrint=new JSONObject();
			JSONArray array=new JSONArray();
			
			eTP.scheduleAtFixedRate(new Runnable() {
				
				@Override
				public void run() {
					
					
					JSONObject toFile = new JSONObject();
					Weather weather = new Weather();
					try {
						
						weather=getWeather(name);//meteo = 
						//System.out.println("meteo " +weather);
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//System.out.println(toFile);
					toFile = printInfo(weather, false);
					
					//System.out.println(toFile);
					array.add(toFile);
					//System.out.println("ARRAY"+array);
					
					toPrint.put("Forecasts", array);
					//System.out.println("TOPRINT"+toPrint);
					
					try {
						if (!file.exists()) {
							file.createNewFile();
							System.out.println("File created");
						}
						FileWriter f = new FileWriter(file); 
						BufferedWriter n = new BufferedWriter(f);
						n.write(toPrint.toString());
						n.close();
					}
					catch(IOException e)
					{
						System.out.println(e); //creare eccezioni
					}
					
					
				}
				
				
			}, 0, 10, TimeUnit.SECONDS);
			
			Stats s=new Stats();
			if(!stats) {
				JSONObject ret=new JSONObject();
				ret.put("File salvato in",   file);
				return ret;
			}else {
				return s.getFiveDaysAverage(toPrint);
			}
			
		}
		
		
		public void ResetUrl() {
			this.url = "https://api.openweathermap.org/data/2.5/";
		}


		
}
