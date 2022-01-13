package univpm.OpenWeather.Utils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Questa classe testa 
 * @author lucas
 */
class UtilsTest {

	@Autowired
	Utils u=new Utils();
	JSONObject esRisposta=new JSONObject();
	
	

	
	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Setting it up!");
		String json = "";
		Scanner in = new Scanner(new FileReader("risposta.txt"));
		while (in.hasNext()) {
			json += (in.nextLine());
		}

		JSONParser parser = new JSONParser();
		JSONObject obj = new JSONObject();
		obj = (JSONObject) parser.parse(json);
		this.esRisposta=obj;
		in.close();
	}

	
	

	/**
	 * Test method for {@link univpm.OpenWeather.Utils.Utils#searchArray(org.json.simple.JSONObject, java.lang.String, java.lang.String)}.
	 * testa un metodo implementato per cercare un valore all'interno di un array a sua volta all'interno di un oggetto
	 * @author lucas
	 * @throws Exception 
	 */
	@Test
	void testSearchArray() throws Exception {
		
		String totest = u.searchArray(this.esRisposta, "weather", "description");
		String corretta = "overcast clouds";

		assertEquals(totest, corretta);

	}

	/**
	 * Test method for {@link univpm.OpenWeather.Utils.Utils#dateConverter(long)}.
	 * testa la conversione in data da stringa
	 * @author lucas
	 * @throws ParseException 
	 */
	@Test
	void testDateConverter() throws ParseException {
		String dt_txt="2022-01-13 09:00:00";
		Date totest=u.dateConverter(dt_txt);
		Date actual=new SimpleDateFormat("yyyy-MM-dd HH:MM:SS").parse(dt_txt);
		
		assertEquals(totest,actual);
		
		
	}

	/**
	 * Test method for {@link univpm.OpenWeather.Utils.Utils#toDate(long)}.
	 * testa la conversione in data da epochtime
	 * @author lucas
	 */
	@Test
	void testToDate() {
		Date tDate=new Date(0);
		Date totest=u.toDate(0);
		assertEquals(tDate,totest);
	}

	/**
	 * Test method for {@link univpm.OpenWeather.Utils.Utils#tempConverter(double)}.
	 * testa la conversione della temperatura in gradi Celsius
	 * @author lucas
	 */
	@Test
	void testTempConverter() {
		double totest=u.tempConverter(293);
		//System.out.println(totest);
		double temp=19.85;
		
		assertEquals(totest,temp);
	}
	
	@AfterEach
	public void tearDown() {
		System.out.println("Tearing down");
		this.esRisposta=null;
	}

}
