package univpm.OpenWeather.Controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import univpm.OpenWeather.Exception.ExeededDayException;
import univpm.OpenWeather.Exception.NullObjectException;
import univpm.OpenWeather.Service.WeatherImpl;
import univpm.OpenWeather.Utils.FiltersImpl;
import univpm.OpenWeather.Utils.Stats;


@RestController
public class OpenWeatherController {
	//private boolean current=true;
	
	@Autowired
	WeatherImpl service; 	
	Stats statistics = new Stats();
	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value="name",defaultValue="Mario")String name) {
		return "Salve "+name+", questo è OpenWeather";		
	}
	
	/**
	 * cambio solo queste prime due rotte
	 * @param name nome della città
	 * @return JSONObject con la rappresentazione della città
	 * @throws MalformedURLException avvisa se la chiamata non è andata a buon fine
	 * 
	 * @author lucas
	 */	
	@GetMapping("/current")
	public ResponseEntity<JSONObject> current(@RequestParam(name = "name", defaultValue = "Milano")String name) throws Exception{
		return new ResponseEntity<>(service.printInfo(service.getWeather(name), true), HttpStatus.OK);
	}
	
	@GetMapping("/forecast")
	public ResponseEntity<JSONObject> forecast(@RequestParam(name = "name", defaultValue = "Milano")String name) throws Exception{
		return new ResponseEntity<>(service.getForecast(name), HttpStatus.OK);
	}
	
	
	/**
	 * @param name
	 * @return la stringa che indica il percorso in cui il file è stato salvato
	 */
	@GetMapping("/saveFile")
	public ResponseEntity<String> saveFile(@RequestParam(name = "name", defaultValue = "Milano")String name){
		return new ResponseEntity<>(service.saveFile(name), HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param name
	 * @return Statistiche sulle previsioni prese da file
	 * @throws NullObjectException
	 * @throws IOException
	 */
	@GetMapping("/Stats")
	public ResponseEntity<JSONObject> genStats(@RequestParam(name = "name", defaultValue="Milano")String name) throws NullObjectException, IOException{
		return new ResponseEntity<JSONObject>(statistics.getFiveDaysAverage(name+"HourlyWeather.txt"), HttpStatus.OK);
	}
	
	@GetMapping("/Errors")
	public ResponseEntity<JSONObject> errors(@RequestParam(name = "name", defaultValue = "Milano")String name) throws MalformedURLException, ParseException{
		return new ResponseEntity<>(service.getErrors(name), HttpStatus.OK);
	}
	
	@GetMapping("/Filters")
	public ResponseEntity<JSONObject> filters(@RequestParam(name = "name", defaultValue = "Milano")String name,
			@RequestParam(name = "start", defaultValue = "now")String start,
			@RequestParam(name = "finish", defaultValue = "five")String finish,
			@RequestParam(name = "time", defaultValue = "00:00")String time) throws MalformedURLException, ParseException, ExeededDayException {
		
		FiltersImpl f=new FiltersImpl(name);
		if(start.equals("now")) {
			start=f.setDate(0);
		}
		if(finish.equals("five")) {
			finish=f.setDate(345600);
		}
		
		return new ResponseEntity<>(f.FromStartToFinish(start+" "+time, finish+" "+time), HttpStatus.OK);
	}
}

