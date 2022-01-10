package univpm.OpenWeather.Exception;

import java.io.IOException;
/**
 * Exception che viene chiamata quando
 * non vinene trovata una risposta alla chiamata effettuata
 * @author Francesco
 *
 */
public class ResposneNotFoundException extends IOException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mes="Response not found";
	/**
	 * Constructor
	 * @param mes
	 */
	public ResposneNotFoundException(String mes) {
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
