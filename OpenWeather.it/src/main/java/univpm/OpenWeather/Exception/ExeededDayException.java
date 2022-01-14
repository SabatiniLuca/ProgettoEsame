package univpm.OpenWeather.Exception;

@SuppressWarnings("serial")
public class ExeededDayException extends Exception {

	public ExeededDayException() {
		System.out.println("Il giorno inserito non è valido");
	}

	public ExeededDayException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}


}
