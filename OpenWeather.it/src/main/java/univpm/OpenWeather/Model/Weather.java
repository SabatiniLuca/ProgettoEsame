package univpm.OpenWeather.Model;


public class Weather extends City {
	
	/**
	 * ho cambiato description (prima era feels like)
	 */
	private String description; //Descrizione del tempo (es. "Soleggiato")
	private double temp;
	private double temp_min;
	private double temp_max;
	private double pressure; 
	private long date;
	private String main;
	private City city;
	

	
	//Constructors
	public Weather(double temp, String description, double temp_min, double temp_max, double pressure, long date, City city) {
		this.temp=temp;
		this.description = description;
		this.temp_min = temp_min;
		this.temp_max = temp_max;
		this.pressure = pressure;
		this.date = date;
		this.city = city;
	}
	

	public Weather(long id, String name, Position coordinates) {
		// TODO Auto-generated constructor stub
		super(id, name, coordinates);	
		}

	public Weather() {
		// TODO Auto-generated constructor stub
	}

	public static double convert(double fTemp) {
		double t=((fTemp-32)*(5/9));
		return  t;
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


	
	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
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
				+ temp_max + ", pressure=" + pressure + ", date=" + date + "]";
	}


	public City getCity() {
		return city;
	}


	public void setCity(City city) {
		this.city = city;
	}

	
	
	

}
