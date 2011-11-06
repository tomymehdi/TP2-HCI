package services;



public class SecurityRequest extends Request {
	
	public static final String SECURE_URL = "http://eiffel.itba.edu.ar/hci/service/Security.groovy?method=";
	
	public SecurityRequest(String method) {
		this(method, null);
	}

	public SecurityRequest(String method, String tosend) {
		super(SECURE_URL + method, tosend);
	}
}