package univpm.OpenWeather.Exception;

/**
 * Exception che si genera se un oggetto (Credo possiamo utilizzarla anche per i
 * JSONObject) è Null
 * 
 * @author Francesco
 *
 */
@SuppressWarnings("serial")
public class WrongValueException extends Exception {

	public WrongValueException() {
		System.out.println("Error: Wrong value detected");
	}

	public WrongValueException(String mes) {
		super(mes);
	}

}
