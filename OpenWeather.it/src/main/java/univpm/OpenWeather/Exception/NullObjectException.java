package univpm.OpenWeather.Exception;
/**
 * Exception che si genera se un oggetto 
 * (Credo possiamo utilizzarla anche per i JSONObject) è Null
 * @author Francesco
 *
 */
public class NullObjectException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mes="Objcet is null";
	/**
	 * Constructor
	 * @param mes
	 */
	public NullObjectException(String mes) {
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
