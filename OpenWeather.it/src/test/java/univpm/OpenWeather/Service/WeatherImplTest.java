package univpm.OpenWeather.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import junit.framework.*;
import univpm.OpenWeather.Utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Questa classe testa alcuni metodi del service
 * @author lucas
 *
 */
class WeatherImplTest extends TestCase { 
	
	@Autowired
	private WeatherImpl service=new WeatherImpl();
	private Utils u=new Utils();
	private String url ="https://api.openweathermap.org/data/2.5/";
	JSONObject esRisposta=new JSONObject();
	/**
	 * Test method for {@link univpm.OpenWeather.Service.WeatherImpl#UrlBuilder(boolean, java.lang.String)}.
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public void setUp() throws IOException, ParseException {
		String str="";
		BufferedReader r=new BufferedReader(new FileReader("risposta.txt"));
		
		while(r.readLine()!=null) {
			str+=r.readLine();
			System.out.println(str);
		}
		r.close();
		JSONParser parser=new JSONParser();
		this.esRisposta=(JSONObject) parser.parse(str);
		
	}
	
	/**
	 * verifica che l'URL per la chiamata sia costruito correttamente.
	 * @author lucas
	 */
	@Test
	void testUrlBuilder() {
		String url=service.UrlBuilder(true,"fano");
		String urlCorretto="https://api.openweathermap.org/data/2.5/weather?q=fano&appid=15b8b402dfd9f2d93b1bfa8245d0edc6";
		assertEquals(url, urlCorretto);
		
	}

	/**
	 * verifica che il metodo per prendere un certo valore all'interno di un JSONArray sia corretto
	 * @throws IOException
	 * @throws ParseException
	 * @author lucas
	 */
	@Test
	void testSearchArray() throws IOException, ParseException {
		setUp();
		String totest=u.searchArray(this.esRisposta, "weather", "description");
		String actual="overcast clouds";
		
		assertEquals(totest,actual);
	}

	/**
	 * verifica che l'URL sia resettato correttamente.
	 * @author lucas
	 */
	@Test
	void testResetUrl() {
		service.ResetUrl();
		String url="https://api.openweathermap.org/data/2.5/";
		assertEquals(this.url,url);
	}

	public void tearDown() {
		this.u=null;
		this.esRisposta=null;
		this.service=null;
	}
}
