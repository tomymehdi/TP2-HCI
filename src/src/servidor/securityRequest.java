package servidor;


public class securityRequest extends Request {
	
	public static final String SECURE_URL = "http://eiffel.itba.edu.ar/hci/service/Security.groovy?method=";
	
	public securityRequest(String method) {
		this(method, null);
	}

	public securityRequest(String method, String tosend) {
		super(SECURE_URL + method, tosend);
	}
}