package univpm.OpenWeather.Utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import univpm.OpenWeather.Exception.NullObjectException;
import univpm.OpenWeather.Model.Weather;

public class Stats implements StatsInt{

	/**
	 * Metodo che crea un JSONObject contenente le statistiche 
	 * per la temperatura e pressione
	 * @author Francesco
	 */
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getFiveDaysAverage(JSONObject jobj) throws NullObjectException {
		// TODO Auto-generated method stub
		JSONObject temp = new JSONObject();
		JSONObject statistics = new JSONObject();
		double t_average = getTempAverage(jobj);
		double temp_max = getTempMax(jobj);
		double temp_min = getTempMin(jobj);
		double p_average = getPressAverage(jobj);
		double p_max = getPressMax(jobj);
		double p_min = getPressMin(jobj);
		if(t_average!=0 && p_average!=0) {
			temp.put("Average Temperature", t_average);
			temp.put("Max Temperature", temp_max);
			temp.put("Min Temperature", temp_min);	
			temp.put("Average Pressure", p_average);
			temp.put("Max Pressure", p_max);
			temp.put("Min Pressure", p_min);	
			statistics.put("Statistics", temp);
		}
		else throw new NullObjectException("Object is null");
		return statistics;
	}
	
	/**
	 * 
	 * @param jobj
	 * @return media delle temperature
	 */
	public double getTempAverage(JSONObject jobj) {
		
		JSONObject temp = null;
		double value = 0;
		double somma =0;
		JSONArray arr = (JSONArray) jobj.get("Forecasts");
		System.out.println(arr);
		for (int i =0; i<arr.size(); i++) {
			JSONObject FObj = (JSONObject) arr.get(i);
			System.out.println(FObj);
			temp = (JSONObject) FObj.get("Temperatures");
			System.out.println(temp);
			
			String curr = (String) temp.get("Current");
			value= Double.parseDouble(curr.substring(0, 4));
			somma +=value;
			//System.out.println(value);
			//System.out.println(somma);
		}
		System.out.println("Somma " + somma);
		return somma/arr.size();
		
	}
	
	/**
	 * 
	 * @param jobj
	 * @return la temperatura massima tra le temperature presenti nel forecast
	 */
	public double getTempMax(JSONObject jobj) {
		
		JSONObject temp = null;
		double value = 0;
		double temp_max =0;
		JSONArray arr = (JSONArray) jobj.get("Forecasts");
		System.out.println(arr);
		for (int i =0; i<arr.size(); i++) {
			JSONObject FObj = (JSONObject) arr.get(i);
			System.out.println(FObj);
			temp = (JSONObject) FObj.get("Temperatures");
			System.out.println(temp);
			
			String curr = (String) temp.get("Current");
			value= Double.parseDouble(curr.substring(0, 4));
			if (value > temp_max) {
				temp_max = value;
			}
			System.out.println(value);
			System.out.println(temp_max);
		}
		return temp_max;
	}
	/**
	 * 
	 * @param jobj
	 * @return la temperatura minima tra le temperature presenti nel forecast
	 */
	public double getTempMin(JSONObject jobj) {
		
		JSONObject temp = null;
		double value = 0;
		double temp_min = 40 ;
		JSONArray arr = (JSONArray) jobj.get("Forecasts");
		System.out.println(arr);
		for (int i =0; i<arr.size(); i++) {
			JSONObject FObj = (JSONObject) arr.get(i);
			System.out.println(FObj);
			temp = (JSONObject) FObj.get("Temperatures");
			System.out.println(temp);
			
			String curr = (String) temp.get("Current");
			value= Double.parseDouble(curr.substring(0, 4));
			if (value < temp_min) {
				temp_min = value;
			}
			System.out.println(value);
			System.out.println(temp_min);
		}
		return temp_min;
	}
	/**
	 * 
	 * @param jobj
	 * @return la pressione media 
	 */
public double getPressAverage(JSONObject jobj) {
		
		JSONObject temp = null;
		double value = 0;
		double somma =0;
		JSONArray arr = (JSONArray) jobj.get("Forecasts");
		System.out.println(arr);
		for (int i =0; i<arr.size(); i++) {
			JSONObject FObj = (JSONObject) arr.get(i);
			System.out.println(FObj);
			temp = (JSONObject) FObj.get("Status");
			System.out.println(temp);
			
			String curr = (String) temp.get("Pressure");
			value= Double.parseDouble(curr.substring(0, 4));
			somma +=value;
			//System.out.println(value);
			//System.out.println(somma);
		}
		System.out.println("Somma " + somma);
		return somma/arr.size();
		
	}
	/**
	 * 
	 * @param jobj
	 * @return la pressione massima tra le pressioni presenti nel forecast
	 */
	public double getPressMax(JSONObject jobj) {
		
		JSONObject temp = null;
		double value = 0;
		double temp_max =0;
		JSONArray arr = (JSONArray) jobj.get("Forecasts");
		System.out.println(arr);
		for (int i =0; i<arr.size(); i++) {
			JSONObject FObj = (JSONObject) arr.get(i);
			System.out.println(FObj);
			temp = (JSONObject) FObj.get("Status");
			System.out.println(temp);
			
			String curr = (String) temp.get("Pressure");
			value= Double.parseDouble(curr.substring(0, 4));
			if (value > temp_max) {
				temp_max = value;
			}
			System.out.println(value);
			System.out.println(temp_max);
		}
		return temp_max;
	}
	
	/**
	 * 
	 * @param jobj
	 * @return  la pressione minima tra le pressioni presenti nel forecast
	 */
	public double getPressMin(JSONObject jobj) {
		
		JSONObject temp = null;
		double value = 0;
		double temp_min = 1100 ;
		JSONArray arr = (JSONArray) jobj.get("Forecasts");
		System.out.println(arr);
		for (int i =0; i<arr.size(); i++) {
			JSONObject FObj = (JSONObject) arr.get(i);
			System.out.println(FObj);
			temp = (JSONObject) FObj.get("Status");
			System.out.println(temp);
			
			String curr = (String) temp.get("Pressure");
			value= Double.parseDouble(curr.substring(0, 4));
			if (value < temp_min) {
				temp_min = value;
			}
			System.out.println(value);
			System.out.println(temp_min);
		}
		return temp_min;
	}
	
}
