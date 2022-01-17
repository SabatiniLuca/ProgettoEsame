 package univpm.OpenWeather.Controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import univpm.OpenWeather.Exception.CityNotFoundException;
import univpm.OpenWeather.Exception.EmptyStringException;
import univpm.OpenWeather.Exception.ExeededDayException;
import univpm.OpenWeather.Exception.NullObjectException;
import univpm.OpenWeather.Exception.WrongDateException;
import univpm.OpenWeather.Service.WeatherImpl;
import univpm.OpenWeather.Utils.FiltersImpl;
import univpm.OpenWeather.Utils.Stats;


@RestController
public class OpenWeatherController {
	
	@Autowired
	WeatherImpl service; 	
	Stats statistics = new Stats();
	
	/**
	 * Questa rotta permette di stampare le condizioni meteorologiche della città inserita, se non è inserita nessuna città la scelta di default sarà Milano.
	 * 
	 * @param name nome della città
	 * @return JSONObject rappresentazione delle condizioni meteorologiche della città
	 * @throws MalformedURLException avvisa se la chiamata non è andata a buon fine
	 * @author lucas
	 */	
	@GetMapping("/current")
	public ResponseEntity<JSONObject> current(@RequestParam(name = "name", defaultValue = "Milano")String name) throws Exception{
		return new ResponseEntity<>(service.printInfo(service.getWeather(name), true), HttpStatus.OK);
	}
	
	/**
	 * Questa rotta permette di stampare le previsioni meteorologiche per i successivi 5 giorni dalla chiamata con intervalli di tre ore.
	 * 
	 * @param name della città 
	 * @return JSONObject con le previsioni per i successivi 5 giorni ad intervalli di 3 ore
	 * @throws Exception
	 * @author lucas
	 */
	@GetMapping("/forecast")
	public ResponseEntity<JSONObject> forecast(@RequestParam(name = "name", defaultValue = "Milano")String name) throws Exception{
		return new ResponseEntity<>(service.getForecast(name), HttpStatus.OK);
	}
	
	
	/**
	 * Questa rotta consente di salvare su file le previsioni meteo prese ogni ora. Su questi file saranno poi generate le statistiche.
	 * 
	 * @param name nome della città della quale si vogliono salvare le informazioni
	 * @return la stringa che indica il percorso in cui il file è stato salvato
	 * @author Francesco
	 * @throws EmptyStringException 
	 */
	@GetMapping("/saveFile")
	public ResponseEntity<String> saveFile(@RequestParam(name = "name", defaultValue = "Milano")String name) throws EmptyStringException{
		return new ResponseEntity<>(service.saveFile(name), HttpStatus.OK);
	}
	
/**
 * Questa rotta consente di visualizzare le statistiche riguardanti i valori massimi, minimi, la media e la varianza della pressione e delle temperature
 * @param name nome della città di cui si vogliono generare le statistiche
 * @return
 * @throws NullObjectException
 * @throws IOException eccezione generata se il nome del file di cui si vogliono generare le statistiche non è presente
 * @author Francesco
 */
	@GetMapping("/Stats")
	public ResponseEntity<JSONObject> genStats(@RequestParam(name = "name", defaultValue="Milano")String name) throws NullObjectException, IOException{
		return new ResponseEntity<JSONObject>(statistics.getFiveDaysAverage(name+"HourlyWeather.txt"), HttpStatus.OK);
	}
	
	/**
	 * Questa rotta consente di visualizzare la soglia di errore delle temperatura corrente, massima e minima tra il forecast(previsioni per 5 giorni)
	 * e l'attuale(previsione giornaliera)
	 * 
	 * @param name nome della città della quale si vuole calcolare la soglia di errore tra il forecast e l'attuale
	 * @return
	 * @throws MalformedURLException
	 * @throws ParseException
	 * @throws CityNotFoundException
	 * @author Francesco
	 */
	@GetMapping("/Errors")
	public ResponseEntity<JSONObject> errors(@RequestParam(name = "name", defaultValue = "Milano")String name) throws MalformedURLException, ParseException, CityNotFoundException{
		return new ResponseEntity<>(service.getErrors(name), HttpStatus.OK);
	}
	
	
	/**
	 * Questa rotta permette di stampare tutte le previsioni ad intervalli di tre ore dal momento Start a Finish.
	 * La data deve essere scritta in formato dd-MM-yyyy , mentre l'orario HH-mm .
	 * 
	 * @param name nome della città
	 * @param start data d'inizio
	 * @param finish data di fine
	 * @param startTime orario d'inizio
	 * @param finishTime orario di fine
	 * 
	 * @return
	 * @throws MalformedURLException
	 * @throws ParseException
	 * @throws ExeededDayException se lo start richiesto è a meno di 3 ore dal momento attuale, o se il finish richiesto è a più fi 5 giorni dal momento attuale.
	 * 
	 * @throws WrongDateException se la data d'inizio è posteriore a quella di fine
	 * @author lucas
	 * @throws CityNotFoundException 
	 */
	@GetMapping("/Filters")
	public ResponseEntity<JSONObject> filters(@RequestParam(name = "name", defaultValue = "Milano")String name,
			@RequestParam(name = "start", defaultValue = "now")String start,
			@RequestParam(name = "finish", defaultValue = "five")String finish,
			@RequestParam(name = "startTime", defaultValue = "00:00")String startTime,
			@RequestParam(name = "finishTime", defaultValue = "00:00")String finishTime) throws MalformedURLException, ParseException, ExeededDayException, WrongDateException, CityNotFoundException {
		String format="dd-MM-yyyy";
		if(!start.equals(format)) {
			
		}
		FiltersImpl f=new FiltersImpl(name);
		if(start.equals("now")) {
			start=f.setDate(0);
		}
		if(finish.equals("five")) {
			finish=f.setDate(432000);
		}
		
		return new ResponseEntity<>(f.FromStartToFinish(start+" "+startTime, finish+" "+finishTime), HttpStatus.OK);
	}
}

