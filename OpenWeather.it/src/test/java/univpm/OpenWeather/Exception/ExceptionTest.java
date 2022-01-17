package univpm.OpenWeather.Exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import univpm.OpenWeather.Service.WeatherImpl;
import univpm.OpenWeather.Utils.GetFromCall;

/**
 * Classe che testa l'eccezione CityNotFound e EmptyStringException
 * @author Francesco
 *
 */
public class ExceptionTest {
	
	GetFromCall g;
	WeatherImpl i = new WeatherImpl();
	
	@BeforeEach
	void setUp() throws Exception {
		g=new GetFromCall();
	}

	@AfterEach
	void tearDown() throws Exception {
		this.g=null;
	}
		
	
	@Test
	void createWeather() throws CityNotFoundException{
		Exception e = assertThrows(CityNotFoundException.class, ()->
		g.createWeather(null, true));
		assertEquals("City not found, please enter a different city name", e.getMessage());
	}
	
	@Test
	void saveFile() throws EmptyStringException {
		Exception e = assertThrows(EmptyStringException.class, ()->
		i.saveFile(""));
		assertEquals("Error: something went wrong, please enter a city name", e.getMessage());
	}

}
