package univpm.OpenWeather.Controller;

	

import java.net.MalformedURLException;


import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import univpm.OpenWeather.Service.WeatherImpl;



@RestController
public class OpenWeatherController {
	
	@Autowired
	WeatherImpl service;
		
		
	@GetMapping(value = "/ApiCall")	
	public ResponseEntity<JSONArray> ApiCall(@RequestParam String cityName) throws MalformedURLException {
		return new ResponseEntity<>(service.getAPI(cityName), HttpStatus.OK);
	}

}
