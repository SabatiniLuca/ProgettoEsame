package univpm.OpenWeather.Utils;

import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import univpm.OpenWeather.Service.WeatherImpl;

/**
 * questa classe implementa l'interfaccia FiltersInt e da la possibilità di selezionare un periodo di tempo 
 * sul quale far visualizzare le previsioni, che saranno stampate grazie a PrintInfo nel service.
 * @author lucas
 *
 */
public class FiltersImpl implements FiltersInt {
	private JSONObject forecast;
	private JSONArray days;
	

	
	WeatherImpl service=new WeatherImpl();
	Utils u=new Utils();
	
	
	public FiltersImpl(String name) throws MalformedURLException, ParseException {
		this.setForecast(service.getForecast(name));
		this.days=(JSONArray) this.forecast.get("Forecasts");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject selectDay(String day) throws ParseException {
		
		Iterator<JSONObject> i=days.iterator();
		JSONArray toPrint= new JSONArray();
		Date dataRichiesta=u.dateConverter(day);
		
		while(i.hasNext() ) {
			JSONObject temp=i.next();
			String tempDate=(String) temp.get("date");
			//String sub=tempDate.substring(0,10);
			Date actual=u.dateConverter(tempDate);
			
			System.out.println("richiesta (17 gen)"+dataRichiesta+"\n");
			System.out.println(actual+"\tpresa dal ogg\n");
			if(dataRichiesta.compareTo(actual)>=0) {
				toPrint.add(temp);
			}else {break;}
		}
		
		JSONObject printAll=new JSONObject();
		printAll.put("Previsioni fino al giorno: "+ dataRichiesta.toString(), toPrint);
		return printAll;
	}

	@Override //da finire
	public JSONObject fiveDaysFromNow() throws ParseException {
		Date date = u.toDate(System.currentTimeMillis()/1000);
		
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy HH:MM");
		  String text = format.format(date);
		  //LocalDate parsedDate = LocalDate.parse(text, format);
		  System.out.println(text);
		
		
		
		return selectDay(text);
	}

	@Override
	public JSONObject oneDayFromNow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject onlyToday() {
		// TODO Auto-generated method stub
		return null;
	}

	public JSONObject getForecast() {
		return forecast;
	}

	public void setForecast(JSONObject forecast) {
		this.forecast = forecast;
	}
	
	
	

}
