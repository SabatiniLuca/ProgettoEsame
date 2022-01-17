package univpm.OpenWeather.Utils;

import java.text.ParseException;

import org.json.simple.JSONObject;

import univpm.OpenWeather.Exception.ExeededDayException;
import univpm.OpenWeather.Exception.WrongDateException;

public interface FiltersInt {

	public JSONObject FromStartToFinish(String start, String finish)
			throws ParseException, ExeededDayException, WrongDateException;

	/**
	 * Questo metodo crea una data sotto forma di testo che limita la stampa di
	 * previsioni e poi le stampa. per esempio se la data creata è fra due giorni
	 * 
	 * @throws ExeededDayException
	 */
	String setDate(long number) throws ParseException, ExeededDayException;

}
