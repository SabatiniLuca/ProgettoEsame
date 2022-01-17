package univpm.OpenWeather.Exception;

/**
 * Exception che viene chiamata quando una stringa è vuota ma non dovrebbe
 * 
 * @author Francesco
 *
 */
@SuppressWarnings("serial")
public class EmptyStringException extends Exception {

	public EmptyStringException() {
		System.out.println("Error: something went wrong, please enter a city name");
	}

	public EmptyStringException(String mes) {
		super(mes);
	}

}
