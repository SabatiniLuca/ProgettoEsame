package univpm.OpenWeather.Exception;

/**
 * classe per eccezioni sul inserimento 
 * errato del nome di una città
 * @author Francesco
 */

public class CityNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; //Se lo tolgo mi da Warning
	public String mes = "City not found, please enter a different city";
	
	/**
	 * Constructor 
	 * @param mes rappresenta il messaggio di errore
	 */
	public CityNotFoundException(String mes) {
		super();
		this.mes = mes;
	}
	
	/**
	 * getter del messaggio di errore
	 * @return messaggio di errore
	 */
	public String getCityNotFoundException() {
		return mes;
	}

}
