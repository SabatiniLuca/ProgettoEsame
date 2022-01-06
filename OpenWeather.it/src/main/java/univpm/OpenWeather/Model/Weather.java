package univpm.OpenWeather.Model;


public class Weather extends City {
	
	private String description; //Descrizione del tempo (es. "Soleggiato")
	private double temp;
	private double temp_min;
	private double temp_max;
	private double pressure; 
	private String data;
	private String main;

	
	//Constructors
	public Weather(double temp, String description, double temp_min, double temp_max, double pressure) {
		this.temp=temp;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	
	public void setDescription(String feels_like) {
		this.description = feels_like;
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
		return "Weather [feels_like=" + description + ", temp_min=" + temp_min + ", temp_max="
				+ temp_max + ", pressure=" + pressure + ", data=" + data + "]";
	}

	
	
	

}
