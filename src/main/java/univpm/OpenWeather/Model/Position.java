package univpm.OpenWeather.Model;

public class Position {

	private double latitude;
	private double longitude;
	
	public Position(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Position() {
		
	}
	
	public double getLat() {
		return latitude;
	}
	public void setLat(double latitude) {
		this.latitude = latitude;
	}
	public double getLon() {
		return longitude;
	}
	public void setLon(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Position [lat=" + latitude + ", lon=" + longitude + "]";
	}
	
}