package univpm.OpenWeather.Service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import junit.framework.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lucas
 *
 */
class WeatherImplTest extends TestCase {
	
	@Autowired
	private WeatherImpl service;

	/**
	 * Test method for {@link univpm.OpenWeather.Service.WeatherImpl#UrlBuilder(boolean, java.lang.String)}.
	 */
	@Test
	void testUrlBuilder() {
		String url=service.UrlBuilder(true,"fano");
		String urlCorretto="api.openweathermap.org/data/2.5/weather?q=fano&appid=15b8b402dfd9f2d93b1bfa8245d0edc6";
		//assertEquals(url, urlCorretto);
		assert url.equals(urlCorretto);
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link univpm.OpenWeather.Service.WeatherImpl#getInfo(java.lang.String)}.
	 */
	@Test
	void testGetInfo() {
		fail("Not yet implemented");
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
		fail("Not yet implemented");
	}

	public void tearDown() {}
}
