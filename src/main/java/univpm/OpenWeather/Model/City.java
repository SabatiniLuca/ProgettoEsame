package univpm.OpenWeather.Model;

public class City extends Position {
<<<<<<< HEAD
	
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
=======
		
		private int id;
		private String cityName;
		private String country;
	
		public City(int id, String cityName, String country, double latitude, double longitude) {
			super(latitude, longitude);
			this.id = id;
			this.cityName = cityName;
			this.country = country;
		}
		
	
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return cityName;
		}
		public void setName(String cityName) {
			this.cityName = cityName;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}


}


>>>>>>> refs/remotes/Francesco/FrancescoUpdate
