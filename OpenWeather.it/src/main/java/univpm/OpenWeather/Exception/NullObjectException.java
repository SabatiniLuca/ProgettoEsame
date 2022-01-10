package univpm.OpenWeather.Exception;

public class NullObjectException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mes;
	
	public NullObjectException(String mes) {
		super();
		this.mes = mes;
	}

	public String getMes() {
		return mes;
	}
}
