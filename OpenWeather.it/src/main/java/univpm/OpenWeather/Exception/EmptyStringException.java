package univpm.OpenWeather.Exception;
/**
 * Exception che viene chiamata quando 
 * una stringa è vuota ma non dovrebbe
 * @author Francesco
 *
 */
public class EmptyStringException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String mes;
	/**
	 * Constructor
	 * @param mes
	 */
	
	public EmptyStringException(String mes) {
		super();
		this.mes = mes;
	}
	
	/**
	 * getter del messaggio di errore
	 * @return messaggio di errore
	 */
	public String getMes() {
		return mes;
	}
	
	

}
