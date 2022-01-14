package univpm.OpenWeather.Utils;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import univpm.OpenWeather.Exception.NullObjectException;


public class Stats implements StatsInt{

	/**
	 * Metodo che crea un JSONObject contenente le statistiche 
	 * per la temperatura e pressione
	 * @author Francesco
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getFiveDaysAverage(String nameFile) throws NullObjectException, IOException {

		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader(nameFile));
		String lettura = br.readLine();
		System.out.println(lettura);
		br.close();
		JSONParser parser = new JSONParser();
		JSONObject read = null;
		try {
			read = (JSONObject) parser.parse(lettura);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONObject temp = new JSONObject();
		JSONObject statistics = new JSONObject();
		
		double t_average = getTempAverage(read);
		double temp_max = getTempMax(read);
		double temp_min = getTempMin(read);
		double p_average = getPressAverage(read);
		double p_max = getPressMax(read);
		double p_min = getPressMin(read);
		
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
	public double getTempAverage(JSONObject obj) {

		JSONObject temp = null;
		double value = 0;
		double somma =0;
		JSONArray arr = (JSONArray) obj.get("Forecasts");

		for (int i =0; i<arr.size(); i++) {
			JSONObject FObj = (JSONObject) arr.get(i);

			temp = (JSONObject) FObj.get("Temperatures");

			String curr = (String) temp.get("Current");
			
			value= Double.parseDouble(curr.substring(0, 4));
			
			somma +=value;

		}
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

		for (int i =0; i<arr.size(); i++) {
			JSONObject FObj = (JSONObject) arr.get(i);

			temp = (JSONObject) FObj.get("Temperatures");

			String curr = (String) temp.get("Current");

			value= Double.parseDouble(curr.substring(0, 4));

			if (value > temp_max) {
				temp_max = value;
			}
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
		
		for (int i =0; i<arr.size(); i++) {
			
			JSONObject FObj = (JSONObject) arr.get(i);
			
			temp = (JSONObject) FObj.get("Temperatures");
			
			String curr = (String) temp.get("Current");
			
			value= Double.parseDouble(curr.substring(0, 4));
			
			if (value < temp_min) {
				temp_min = value;
			}
			
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
		
		for (int i =0; i<arr.size(); i++) {
			
			JSONObject FObj = (JSONObject) arr.get(i);
			
			temp = (JSONObject) FObj.get("Status");
			
			String curr = (String) temp.get("Pressure");
			
			value= Double.parseDouble(curr.substring(0, 4));
			
			somma +=value;
		}
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
		
		for (int i =0; i<arr.size(); i++) {
			
			JSONObject FObj = (JSONObject) arr.get(i);
			
			temp = (JSONObject) FObj.get("Status");
			
			String curr = (String) temp.get("Pressure");
			
			value= Double.parseDouble(curr.substring(0, 4));
			
			if (value > temp_max) {
				temp_max = value;
			}
		}
		return temp_max;
	}

	/**
	 * @param jobj
	 * @return  la pressione minima tra le pressioni presenti nel forecast
	 */
	public double getPressMin(JSONObject jobj) {

		JSONObject temp = null;
		double value = 0;
		double temp_min = 1100 ;
		JSONArray arr = (JSONArray) jobj.get("Forecasts");
		
		for (int i =0; i<arr.size(); i++) {
			
			JSONObject FObj = (JSONObject) arr.get(i);
			
			temp = (JSONObject) FObj.get("Status");
			
			String curr = (String) temp.get("Pressure");
			
			value= Double.parseDouble(curr.substring(0, 4));
			
			if (value < temp_min) {
				temp_min = value;
			}
		}
		return temp_min;
	}


	//Da finire
	public double getVariance(JSONObject jobj) {
		JSONObject variance = null;
		double media = getPressAverage(jobj);
		double somma=0;
		double var=0;
		JSONArray arr =(JSONArray) jobj.get("Forecasts");
		for (int i =0; i<arr.size(); i++) {
			JSONObject FObj = (JSONObject) arr.get(i);
			System.out.println(FObj);
			variance = (JSONObject) FObj.get("Status");
			System.out.println(variance);

			String curr = (String) variance.get("Pressure");
			double value= Double.parseDouble(curr.substring(0, 4));
			somma +=value;
		}
		//var = pow((somma - media), 2) 
		return var;
	}

}
