package univpm.OpenWeather.Service;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import junit.framework.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lucas
 *
 */
class WeatherImplTest extends TestCase {
	
	@Autowired
	private WeatherImpl service=new WeatherImpl();
	private String url ="https://api.openweathermap.org/data/2.5/";
	/**
	 * Test method for {@link univpm.OpenWeather.Service.WeatherImpl#UrlBuilder(boolean, java.lang.String)}.
	 */
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
		//String url="https://api.openweathermap.org/data/2.5/weather?q=fano&appid=15b8b402dfd9f2d93b1bfa8245d0edc6";
		//obj=service.getInfo(url);
		
		
		//assertEquals(obj , line);
	}

	/**
	 * Test method for {@link univpm.OpenWeather.Service.WeatherImpl#getWeather(java.lang.String, univpm.OpenWeather.Model.Weather)}.
	 */
	@Test
	void testGetWeather() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link univpm.OpenWeather.Service.WeatherImpl#printInfo(univpm.OpenWeather.Model.Weather, boolean)}.
	 */
	@Test
	void testPrintInfo() {
		fail("Not yet implemented");
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
