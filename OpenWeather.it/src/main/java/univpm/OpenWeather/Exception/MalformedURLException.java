package univpm.OpenWeather.Exception;
/**
 * Exception che si genera se l'URL 
 * passato è errato
 * @author Francesco
 *
 */
public class MalformedURLException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mes = "Error: URL entered has several problem";
	/**
	 * Constructor
	 * @param mes
	 */
	public MalformedURLException(String mes) {
		this.mes = mes;
	}
	/**
	 * getter del messaggio di errore
	 * @return messaggio di errore
	 */
	public String getMes() {
		return this.mes;
	}

}
