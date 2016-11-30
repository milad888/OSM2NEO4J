package testApi;


//import java.lang.*;
//import java.io.*;

public class Tool {
	
	public static double distanceCal(String Lat1, String Lon1, String Lat2, String Lon2) {
		double lat1 = Double.parseDouble(Lat1);
		double lon1 = Double.parseDouble(Lon1);
		double lat2 = Double.parseDouble(Lat2);
		double lon2 = Double.parseDouble(Lon2);
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 1.609344;
		
		return (dist);
	}
	
	
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
	
	
	
	
	 // declare public constants
	  
	  /**
	   * The minimum allowed latitude
	   */
	  public static float MIN_LATITUDE = Float.valueOf("-90.0000");
	  
	  /**
	   * The maximum allowed latitude
	   */
	  public static float MAX_LATITUDE = Float.valueOf("90.0000");
	  
	  /**
	   * The minimum allowed longitude
	   */
	  public static float MIN_LONGITUDE = Float.valueOf("-180.0000");
	  
	  /**
	   * The maximum allowed longitude 
	   */
	  public static float MAX_LONGITUDE = Float.valueOf("180.0000");
	  
	  /**
	   * The diameter of the Earth used in calculations
	   */
	  public static float EARTH_DIAMETER = Float.valueOf("12756.274");
	
	
	
		 

		  /**
		   * A method to validate a latitude value
		   *
		   * @param latitude the latitude to check is valid
		   *
		   * @return         true if, and only if, the latitude is within the MIN and MAX latitude
		   */
		  public static boolean isValidLatitude(float latitude) {
		    if(latitude >= MIN_LATITUDE && latitude <= MAX_LATITUDE) {
		      return true;
		    } else {
		      return false;
		    }
		  }
		  
		  /**
		   * A method to validate a longitude value
		   *
		   * @param longitude the longitude to check is valid
		   *
		   * @return          true if, and only if, the longitude is between the MIN and MAX longitude
		   */
		  public static boolean isValidLongitude(float longitude) {
		    if(longitude >= MIN_LONGITUDE && longitude <= MAX_LONGITUDE) {
		      return true;
		    } else {
		      return false;
		    }
		  }
		  
	
}
