package univpm.OpenWeather.Model;

public class City extends Position {
		
		private int id;
		private String name;
		private String descr;
		private double temp_max;
		private double temp_min;
		private double pression;
	
		public City(int id, String name, String descr, double temp_max, double temp_min, double pression, double latitude, double longitude) {
			super(latitude, longitude);
			this.id = id;
			this.name = name;
			this.descr = descr;
			this.temp_max = temp_max;
			this.temp_min = temp_min;
			this.pression = pression;
		}
		
		

		public double getTemp_max() {
			return temp_max;
		}


		public void setTemp_max(double temp_max) {
			this.temp_max = temp_max;
		}


		public double getTemp_min() {
			return temp_min;
		}


		public void setTemp_min(double temp_min) {
			this.temp_min = temp_min;
		}
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescr() {
			return descr;
		}
		public void setDescr(String descr) {
			this.descr = descr;
		}

		public double getPression() {
			return pression;
		}



		public void setPression(double pression) {
			this.pression = pression;
		}



		

}


