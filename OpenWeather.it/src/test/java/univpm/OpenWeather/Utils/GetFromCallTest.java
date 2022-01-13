package univpm.OpenWeather.Utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import junit.framework.TestCase;
import univpm.OpenWeather.Model.City;
import univpm.OpenWeather.Model.Position;
import univpm.OpenWeather.Model.Weather;

/**
 * Questa classe testa i vari metodi che servono all'applicazione
 *  per leggere le informazioni dalla chiamata API
 * @author lucas
 *
 */
class GetFromCallTest extends TestCase {
	@Autowired
	private GetFromCall get=new GetFromCall();

	private String toJson="";
	JSONObject esRisposta=new JSONObject();
	JSONObject parsato=new JSONObject();

	private Position coord;

	private City city;

	private Weather meteo;
	
	
	@BeforeEach
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
		

		this.coord=new Position(43.8204,13.0121);
		this.city=new City(3177219,"Fano",coord);
		this.meteo=new Weather(281.51, "overcast clouds",275.64,282.08,1021,1641886893, "Clouds");
		
		
	}

	

	
	@Test
	void testCreatePosition() throws IOException, ParseException {
		
		assertEquals(get.createPosition(esRisposta).toString(),this.coord.toString());
		
	}

	@Test
	void testCreateCity() throws IOException, ParseException {
		
		assertEquals(get.createCity(esRisposta).toString(),this.city.toString());
		
	}

	@Test
	void testCreateWeather() throws IOException, ParseException {
		
		Weather totest=get.createWeather(this.esRisposta, false);
		
		assertEquals(totest.getCoordinates(),this.meteo.getCoordinates());
		assertEquals(totest.getCity(),this.meteo.getCity());
		
	}

	@Test
	void testGetMain() throws IOException, ParseException {
		
		String totest=get.getMain(this.esRisposta, true).toString();
		String	main="{\"t\":281.51,\"tMax\":282.08,\"tMin\":275.64,\"pressure\":1021}";
		assertEquals(main,totest);
		main=null;
		totest=null;
		
	}

	/**
	 * converte oggetti in double (primitivo)
	 */
	@Test
	void testIfThenRound() {
		Object number=257.0000;
		double d=257;
		double totest=get.ifThenRound(number);
		assertEquals(d,totest);
		
	}

	@Test
	void testGetCity() throws IOException, ParseException {
		
		City totest=get.createCity(esRisposta);
		assertEquals(totest.toString(),this.city.toString());
		
	}
	
	@Test
	void testGetCoord() throws IOException, ParseException {
		
		assertEquals(get.getCoord(this.esRisposta),this.esRisposta.get("coord"));
		
	}

	@SuppressWarnings("unchecked")
	@Test
	void testGetWeather() throws IOException, ParseException {
		
		JSONObject actual=new JSONObject();
		actual.put("Description", "overcast clouds");
		actual.put("Main", "Clouds");
		assertEquals(get.getWeather(this.esRisposta),actual);
		actual=null;
		
	}
	
	@AfterEach
	public void tearDown() {
		this.esRisposta=null;
		this.toJson=null;
		this.coord=null;
		this.city=null;
		this.meteo=null;
		System.out.println("Teared Down");
		
	}

}
