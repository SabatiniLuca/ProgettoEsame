package univpm.OpenWeather.Model;

public class City extends Position {
		
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


