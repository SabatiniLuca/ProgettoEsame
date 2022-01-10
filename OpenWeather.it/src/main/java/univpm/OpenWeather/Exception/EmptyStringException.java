package univpm.OpenWeather.Exception;

public class EmptyStringException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mes;
	
	public EmptyStringException(String mes) {
		super();
		this.mes = mes;
	}
	
	public String getMes() {
		return mes;
	}
	
	

}
