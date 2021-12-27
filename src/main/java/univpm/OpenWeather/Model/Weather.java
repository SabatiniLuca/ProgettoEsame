package univpm.OpenWeather.Model;

public class Weather {

	private double temp_min;
	private double temp_max;
	private double pressure; 
	private String feels_like; //Descrizione del tempo (es. "Nuvoloso")
	private String main; //deve far ritornare il meteo generico di una città
	
	public Weather() {
		this.main = null;
		this.temp_max =0;
		this.temp_min =0;
		this.pressure =0;
		this.feels_like = null;
	}

	public double getTemp_min() {
		return temp_min;
	}

	public void setTemp_min(double temp_min) {
		this.temp_min = temp_min;
	}

	public double getTemp_max() {
		return temp_max;
	}

	public void setTemp_max(double temp_max) {
		this.temp_max = temp_max;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public String getFeels_like() {
		return feels_like;
	}

	public void setFeels_like(String feels_like) {
		this.feels_like = feels_like;
	}

	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}
	
	
	
	
	
}
