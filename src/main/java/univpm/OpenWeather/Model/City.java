package univpm.OpenWeather.Model;

public class City extends Position {
	
	private long id;
	private String name;
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

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + "," + ", coordinates=" + coordinates + "]";
	}


	

}
