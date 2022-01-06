package univpm.OpenWeather.Model;


import java.util.Vector;

public class City extends Position {
	
	private long id;
	private String name;
	private Vector <Weather> meteo = new Vector<Weather>();
	private Position coordinates;
	
	public City(long id, String name, Position coordinates) {
		super();
		this.id = id;
		this.name = name;
		this.coordinates= coordinates;
	}
	
	public Position getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Position coordinates) {
		this.coordinates = coordinates;
	}

	public City(long id, String name) {
		// TODO Auto-generated constructor stub
	}
	
	public City(String name) {
		this.name = name;
	}
	
	
	public City() {
		// TODO Auto-generated constructor stub
		super();
	}

	

	

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	
	public String getCityName() {
		return name;
	}
	public void setCityName(String name) {
		this.name = name;
	}
	
	public Vector<Weather> getMeteo() {
		return meteo;
	}
	public void setMeteo(Vector<Weather> meteo) {
		this.meteo = meteo;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + "," + ", meteo=" + meteo
				+ ", coordinates=" + coordinates + "]";
	}


	

}
