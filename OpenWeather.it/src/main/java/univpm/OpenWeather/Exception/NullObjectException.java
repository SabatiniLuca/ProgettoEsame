package univpm.OpenWeather.Exception;

/**
 * Exception che si genera se un oggetto (Credo possiamo utilizzarla anche per i
 * JSONObject) è Null
 * 
 * @author Francesco
 *
 */
@SuppressWarnings("serial")
public class NullObjectException extends Exception {

	public NullObjectException() {
		System.out.println("Error: Object is null");
	}

	public NullObjectException(String mes) {
		super(mes);
	}

}
