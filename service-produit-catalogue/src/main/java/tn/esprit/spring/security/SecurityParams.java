package tn.esprit.spring.security;

public interface SecurityParams {
	public static final String JWT_HEADERNAME="Authorization";
	public static final String SECRET="noussairhamrit";
	public static final long EXPIRATION=10*24*3600*1000;
	public static final String PREFIX="Bearer ";

}
