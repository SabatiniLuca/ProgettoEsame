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

import univpm.OpenWeather.Exception.ExeededDayException;
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
			Date tempDate=u.dateConverter((String) temp.get("date")) ;
			
			if(dataRichiesta.compareTo(tempDate)>=0) {
				toPrint.add(temp);
			}else {break;}
			tempDate=null;
		}
		
		JSONObject printAll=new JSONObject();
		printAll.put("Previsioni fino al giorno: "+ dataRichiesta.toString().substring(0,16), toPrint);
		return printAll;
	}

	
	/**
	 * Questo metodo crea una data sotto forma di testo che limita la stampa di previsioni e poi le stampa.
	 * per esempio se la data creata è fra due giorni
	 * @throws ExeededDayException 
	 */
	@Override 
	public String setDate(long number) throws ParseException, ExeededDayException {
		long days;//86400  secondi in un giorno
		String text="";
		
		if (number>432000 ) {
			throw new ExeededDayException("La previsione cercata è tra più di 5 giorni da ora");
		}
		else if(number<10800) {
			throw new ExeededDayException("La previsione cercata è tra meno di 3 ore da ora");
		}else if(number==0) {
			days=0;//setta la data al momento presente
		}
		else {
			days=86400+number;//setta la data ad un momento futuro in base a quanto è grande number
		}
		
		Date date = u.toDate((System.currentTimeMillis()/1000)+days);
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy HH:MM");
		text = format.format(date);
		return text;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject FromStartToFinish(String start, String finish) throws ParseException {
		
		Iterator<JSONObject> i=this.days.iterator();
		JSONArray toPrint= new JSONArray();
		
		Date datainizio=u.dateConverter(start);
		while(true) {
			Date cerca=u.dateConverter((String) i.next().get("date")) ;
			if(datainizio.compareTo(cerca)==0) {
				break;
			}
		}
		
		Date datafine=u.dateConverter(finish);
		
		while(i.hasNext() ) {
			JSONObject temp=i.next();
			Date tempDate=u.dateConverter((String) temp.get("date")) ;
			
			if(datafine.compareTo(tempDate)>=0) {
				toPrint.add(temp);
			}else {break;}
			tempDate=null;
		}
		
		JSONObject printAll=new JSONObject();
		printAll.put("Previsioni dal"+ datainizio.toString().substring(0, 16)+" al "+datafine.toString().substring(0, 16), toPrint);
		return printAll;
	}

	
	
	//setters e getter
	public JSONObject getForecast() {
		return forecast;
	}

	public void setForecast(JSONObject forecast) {
		this.forecast = forecast;
	}

	
	
	
	

}
