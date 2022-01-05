package univpm.OpenWeather.Model;


public class Weather extends City {
	
	private String feels_like; //Descrizione del tempo (es. "Soleggiato")
	private double temp;
	private double temp_min;
	private double temp_max;
	private double pressure; 
	private String data;
	private String main;

	
	//Constructors
	public Weather(double temp, String feels_like, double temp_min, double temp_max, double pressure) {
		this.temp=temp;
		this.feels_like = feels_like;
		this.temp_min = temp_min;
		this.temp_max = temp_max;
		this.pressure = pressure;
	}
	

	public Weather(long id, String name, Position coordinates) {
		// TODO Auto-generated constructor stub
		super(id, name, coordinates);	
		}

	public Weather() {
		// TODO Auto-generated constructor stub
	}

	
	//Getters & Setters
	
	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public String getFeels_like() {
		return feels_like;
	}

	
	public void setFeels_like(String feels_like) {
		this.feels_like = feels_like;
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


	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public String getMain() {
		return main;
	}
	
	public void setMain(String main) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public String toString() {
		return "Weather [feels_like=" + feels_like + ", temp_min=" + temp_min + ", temp_max="
				+ temp_max + ", pressure=" + pressure + ", data=" + data + "]";
	}

	
	
	

}
