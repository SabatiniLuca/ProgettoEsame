/**
 * 
 */
package univpm.OpenWeather.Service;

import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import univpm.OpenWeather.Model.City;
import univpm.OpenWeather.Model.Position;
import univpm.OpenWeather.Model.Weather;

/**
 * questa classe testa alcune parti del  @Service 
 * @author lucas
 *
 */
class WeatherImplTest extends TestCase {
	
	private WeatherImpl service=new WeatherImpl();
	private Weather meteo;
	private City city;
	private Position coord;
	private String url ="https://api.openweathermap.org/data/2.5/";
	private String toJson="";
	JSONObject esRisposta=new JSONObject();
	JSONObject parsato=new JSONObject();
	
	@BeforeEach
	@SuppressWarnings("unchecked")
	@Override
	public void setUp() throws IOException, ParseException {
		System.out.println("Setting it up!");
		this.toJson= "";
		Scanner in = new Scanner(new FileReader("risposta.txt"));
		while (in.hasNext()) {
			this.toJson += (in.nextLine());
		}

		JSONParser parser = new JSONParser();
		JSONObject obj = new JSONObject();
		obj = (JSONObject) parser.parse(this.toJson);
		this.esRisposta=obj;
		in.close();
		

		JSONObject actual=new JSONObject();
		actual.put("date","11-01-2022 08:41" );
		JSONObject status= new JSONObject();
		status.put("Weather", "Clouds");
		status.put("Specific", "overcast clouds");
		status.put("Pressure", "1021 Pa");
		actual.put("Status",status);
		JSONObject temperatures=new JSONObject();
		temperatures.put("Minimum", "2.49 °C");
		temperatures.put("Maximum", "8.93 °C");
		temperatures.put("Current", "8.36 °C");
		actual.put("Temperatures", temperatures);
		
		this.parsato=actual;
		actual=null;
		
		this.coord=new Position(43.8204,13.0121);
		this.city=new City(3177219,"Fano",this.coord);
		this.meteo=new Weather(281.51,"overcast clouds",275.64,282.08,1021,1641886893,"Clouds",this.city);
		
	}

	/**
	 * Test method for {@link univpm.OpenWeather.Service.WeatherImpl#UrlBuilder(boolean, java.lang.String)}.
	 */
	@Test
	void testUrlBuilderTrue() {
		String url=service.UrlBuilder(true,"fano");
		String urlCorretto="https://api.openweathermap.org/data/2.5/weather?q=fano,IT&appid=15b8b402dfd9f2d93b1bfa8245d0edc6";
	assertEquals(url, urlCorretto);
		
	}
	
	@Test
	void testUrlBuilderFalse() {
		String urlfor=service.UrlBuilder(false,"fano");
		String urlCorrettofor="https://api.openweathermap.org/data/2.5/forecast?q=fano,IT&appid=15b8b402dfd9f2d93b1bfa8245d0edc6";
		assertEquals(urlCorrettofor,urlfor );
		
	}
	
	/**
	 * Test method for {@link univpm.OpenWeather.Service.WeatherImpl#getWeather(java.lang.String)}.
	 * @throws MalformedURLException 
	 */
	@Test
	void testGetWeather() throws MalformedURLException {
		fail("DA CHIEDERE");
	}

	/**
	 * Test method for {@link univpm.OpenWeather.Service.WeatherImpl#getForecast(java.lang.String)}.
	 */
	@Test
	void testGetForecast() {
		fail("DA CHIEDERE");
	}

	/**
	 * Test method for {@link univpm.OpenWeather.Service.WeatherImpl#printInfo(univpm.OpenWeather.Model.Weather, boolean)}.
	 * @throws ParseException 
	 * @throws IOException 
	 */
	@Test
	void testPrintInfo() throws IOException, ParseException {
		setUp();
		
		JSONObject totest=service.printInfo(this.meteo, false);
		
		
		assertEquals(totest,this.parsato);
	}

	/**
	 * Test method for {@link univpm.OpenWeather.Service.WeatherImpl#saveFile(java.lang.String)}.
	 */
	@Test
	void testSaveFile() {
		fail("DA CHIEDERE");
	}

	/**
	 * Test method for {@link univpm.OpenWeather.Service.WeatherImpl#saveHourlyWeatherAndStats(java.lang.String, boolean)}.
	 */
	@Test
	void testSaveHourlyWeatherAndStats() {
		fail("DA CHIEDERE");
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
	
	@AfterEach
	@Override
	public void tearDown() {
		System.out.println("Tests are done, tearing down.");
		this.esRisposta=null;
		this.toJson=null;
		this.coord=null;
		this.city=null;
		this.meteo=null;
		this.parsato=null;
	}

}
