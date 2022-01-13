package univpm.OpenWeather.Controller;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import univpm.OpenWeather.Exception.NullObjectException;
import univpm.OpenWeather.Model.Weather;
import univpm.OpenWeather.Service.WeatherImpl;
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
	
	@RequestMapping("/current")
	public ResponseEntity<JSONObject> current(@RequestParam(name = "name", defaultValue = "Milano")String name) throws Exception{
		return new ResponseEntity<>(service.printInfo(service.getWeather(name), true), HttpStatus.OK);
	}
	
	@RequestMapping("/forecast")
	public ResponseEntity<JSONObject> forecast(@RequestParam(name = "name", defaultValue = "Milano")String name) throws Exception{
		return new ResponseEntity<>(service.getForecast(name), HttpStatus.OK);
	}
	
	/*@GetMapping("/stats")
	public ResponseEntity<JSONObject> stats (@RequestParam(name="name", defaultValue="Milano")String name) throws ParseException, NullObjectException, IOException{
		JSONObject obj = service.getForecast(name);
		String path = null;
		return new ResponseEntity<>(statistics.getFiveDaysAverage(path), HttpStatus.OK);
	}*/
	
	@GetMapping("/saveArray")
	public ResponseEntity<JSONObject> saveArray (@RequestParam(name = "name", defaultValue="Milano")String name){
		return new ResponseEntity<>(service.saveHourlyWeather(name), HttpStatus.OK);
	}
	
	@GetMapping("/historystats")
	public ResponseEntity<JSONObject> historyStats(@RequestParam(name = "name", defaultValue = "Milano")String name) throws NullObjectException, IOException{
		service.saveHourlyWeather(name);
		return new ResponseEntity<>(statistics.getFiveDaysAverage(name+"HourlyWeather"), HttpStatus.OK);
	}
	
}

