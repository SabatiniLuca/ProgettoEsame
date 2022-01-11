/**
 * 
 */
package univpm.OpenWeather.Utils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileReader;
import java.util.Date;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lucas
 *
 */
class UtilsTest {

	@Autowired
	Utils u=new Utils();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @return 
	 * @throws java.lang.Exception
	 *
	@BeforeEach
	JSONObject setUp() throws Exception {
		JSONObject obj=new JSONObject();
		ObjectInputStream in= new ObjectInputStream(new BufferedInputStream(new FileInputStream("risposta.json") ) );
		obj=(JSONObject) in.readObject();
		return obj;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link univpm.OpenWeather.Utils.Utils#searchArray(org.json.simple.JSONObject, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	void testSearchArray() throws Exception {
		String json = "";
		Scanner in = new Scanner(new FileReader("risposta.json"));
		while (in.hasNext()) {
			json += (in.nextLine());

		}
		System.out.println(json);
		JSONParser parser = new JSONParser();
		JSONObject obj = new JSONObject();
		obj = (JSONObject) parser.parse(json);
		in.close();
		String totest = u.searchArray(obj, "weather", "description");
		String corretta = "overcast clouds";

		assertEquals(totest, corretta);

	}

	/**
	 * Test method for {@link univpm.OpenWeather.Utils.Utils#dateConverter(long)}.
	 */
	@Test
	void testDateConverter() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link univpm.OpenWeather.Utils.Utils#toDate(long)}.
	 */
	@Test
	void testToDate() {
		Date tDate=new Date(0);
		Date totest=u.toDate(0);
		assertEquals(tDate,totest);
	}

	/**
	 * Test method for {@link univpm.OpenWeather.Utils.Utils#tempConverter(double)}.
	 */
	@Test
	void testTempConverter() {
		double totest=u.tempConverter(293);
		//System.out.println(totest);
		double temp=19.85;
		
		assertEquals(totest,temp);
	}

}
