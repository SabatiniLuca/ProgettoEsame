package univpm.OpenWeather.Utils;

import java.io.BufferedReader;


import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import univpm.OpenWeather.Exception.NullObjectException;

/**
 * Questa classe contiene i metodi che servono a leggere un file salvato e calcolare le statistiche riguardanti 
 * valori massimi, minimi, media e varianza di temperatura e pressione di una città prendendo i valori da file
 * @author Francesco
 *
 */
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
		double temp_max = getCurrentMax(read);
		double temp_min = getCurrentMin(read);
		double p_average = getPressAverage(read);
		double p_max = getPressMax(read);
		double p_min = getPressMin(read);
		double varianceP = getPressVariance(read);
		double varianceT = getTempVariance(read);

		if(t_average!=0 && p_average!=0) {
			temp.put("Average Temperature", t_average);
			temp.put("Max Temperature", temp_max);
			temp.put("Min Temperature", temp_min);	
			temp.put("Average Pressure", p_average);
			temp.put("Max Pressure", p_max);
			temp.put("Min Pressure", p_min);
			temp.put("Pressure Variance", varianceP);
			temp.put("Temperature Variance", varianceT);			
			statistics.put("Statistics", temp);
		}
		else throw new NullObjectException("Object is null");

		return statistics;
	}

	/**
	 * Questo metodo consente di fare la media della temperatura corrente, tra le temperature registrate
	 * @param jobj JSONObject contenente le informazioni del file
	 * @return media delle temperature
	 * @author Francesco
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
		return Math.round((somma)/arr.size()*100.0)/100.0;

	}

	/**
	 * Questo metodo consente di prendere la temperatura massima tra le temperature correnti nell'arco di una giornata
	 * @param jobj JSONObject contenente le informazioni del file
	 * @return la temperatura massima tra le temperature presenti nel forecast
	 * @author Francesco
	 */
	public double getCurrentMax(JSONObject jobj) {

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
	 * Questo metodo consente di prendere la temperatura minima tra le temperature correnti nell'arco di una giornata
	 * @param jobjJSONObject contenente le informazioni del file
	 * @return la temperatura minima tra le temperature presenti nel forecast
	 * @author Francesco
	 */
	public double getCurrentMin(JSONObject jobj) {

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
	 * Questo metodo consente di elaborare la varianza della temperatura
	 * @param jobj JSONObject contenente le informazioni del file
	 * @return
	 * @author Francesco
	 */
	public double getTempVariance(JSONObject jobj) {

		JSONObject variance = null;
		double var;
		double somma=0, media=0;

		JSONArray arr =(JSONArray) jobj.get("Forecasts");
		double value[] = new double[arr.size()];
		for (int i =0; i<arr.size(); i++) {

			JSONObject FObj = (JSONObject) arr.get(i);

			variance = (JSONObject) FObj.get("Temperatures");

			String curr = (String) variance.get("Current");

			double valuePress= Double.parseDouble(curr.substring(0, 4));

			somma += valuePress;

			value[i]=valuePress;
		}

		media = somma/arr.size();

		double s=0;

		for (int i=0; i<arr.size(); i++) {
			value[i] = value[i]-media;
			value[i]=Math.pow(value[i],2);
			s +=value[i];
		}

		var = s/(arr.size()-1);

		var = Math.round(var*100.0)/100.0;		
		return var;
	}
	/**
	 * Questo metodo consente di stampare la pressione media di una giornata
	 * @param jobj JSONObject contenente le informazioni del file
	 * @return la pressione media 
	 * @author Francesco
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
		return Math.round((somma/arr.size())*100.0)/100.0;

	}
	/**
	 * Questo metodo consente di prendere la temperatura massima tra le pressioni nell'arco di una giornata
	 * @param jobj JSONObject contenente le informazioni del file
	 * @return la pressione massima tra le pressioni presenti nel forecast
	 * @author Francesco
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
	 * Questo metodo consente di prendere la pressione minima tra le pressioni nell'arco di una giornata
	 * @param jobj JSONObject contenente le informazioni del file
	 * @return  la pressione minima tra le pressioni presenti nel forecast
	 * @author Francesco
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

	/**
	 * Questo metodo consente di elaborare la varianza della pressione
	 * @param jobj JSONObject contenente le informazioni del file
	 * @return
	 * @author Francesco
	 */
	public double getPressVariance(JSONObject jobj) {

		JSONObject variance = null;
		double var;
		double somma=0, media=0;

		JSONArray arr =(JSONArray) jobj.get("Forecasts");
		double value[] = new double[arr.size()];
		for (int i =0; i<arr.size(); i++) {

			JSONObject FObj = (JSONObject) arr.get(i);

			variance = (JSONObject) FObj.get("Status");

			String curr = (String) variance.get("Pressure");

			double valuePress= Double.parseDouble(curr.substring(0, 4));

			somma += valuePress;

			value[i]=valuePress;
		}

		media = somma/arr.size();

		double s=0;

		for (int i=0; i<arr.size(); i++) {
			value[i] = value[i]-media;
			value[i]=Math.pow(value[i],2);
			s +=value[i];
		}

		var = s/(arr.size()-1);

		var = Math.round(var*100.0)/100.0;		
		return var;
	}




}
