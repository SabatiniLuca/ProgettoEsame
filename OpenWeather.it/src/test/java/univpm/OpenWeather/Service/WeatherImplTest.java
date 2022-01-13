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
		System.out.println(this.esRisposta);
	}
	
	
	@Test
	void testUrlBuilder() {
		String url=service.UrlBuilder(true,"fano");
		String urlCorretto="https://api.openweathermap.org/data/2.5/weather?q=fano&appid=15b8b402dfd9f2d93b1bfa8245d0edc6";
		assertEquals(url, urlCorretto);
		
	}

	/**
	 * Test method for {@link univpm.OpenWeather.Service.WeatherImpl#getInfo(java.lang.String)}.
	 * @throws IOException 
	 */
	@Test
	void testGetInfo() throws IOException {
		
	}

	/**
	 * Test method for {@link univpm.OpenWeather.Service.WeatherImpl#getWeather(java.lang.String, univpm.OpenWeather.Model.Weather)}.
	 * @throws ParseException 
	 * @throws IOException 
	 */
	@Test
	void testGetWeather() throws IOException, ParseException {
		setUp();
		String totest=u.searchArray(this.esRisposta, "weather", "description");
		String actual="overcast clouds";
		
		assertEquals(totest,actual);
	}

	/**
	 * Test method for {@link univpm.OpenWeather.Service.WeatherImpl#printInfo(univpm.OpenWeather.Model.Weather, boolean)}.
	 */
	@Test
	void testPrintInfo() {
		
	}

	/**
	 * Test method for {@link univpm.OpenWeather.Service.WeatherImpl#ResetUrl()}.
	 */
	@Test
	void testResetUrl() {
		service.ResetUrl();
		String url="https://api.openweathermap.org/data/2.5/";
		assertEquals(this.url,url);
	}

	public void tearDown() {}
}
