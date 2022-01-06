package univpm.OpenWeather.Model;

<<<<<<< HEAD
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
	
	

=======
public abstract class Position {

	protected double latitude;
	protected double longitude;
	
	public Position(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
>>>>>>> refs/remotes/Francesco/FrancescoUpdate
}
